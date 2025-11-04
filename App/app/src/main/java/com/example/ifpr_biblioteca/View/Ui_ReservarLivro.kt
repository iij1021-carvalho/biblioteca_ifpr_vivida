package com.example.ifpr_biblioteca.View

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ifpr_biblioteca.Data.Dt_LivroReservado
import com.example.ifpr_biblioteca.Data.ReservadoUiState
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModelUsuario
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_Livro
import com.example.ifpr_biblioteca.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("WeekBasedYear")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservarLivro(navController: NavController, livroViewModel: ViewModel_Livro, usuarioViewModel: ViewModelUsuario) {
    val listaLivros by livroViewModel.livro.collectAsState()
    val uiState_ by livroViewModel.uiState_.collectAsState()
    val idusuario by usuarioViewModel.iduser.collectAsState()
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
        ) {
            item {
                Row {
                    Image(
                        modifier = Modifier.size(180.dp),
                        painter = painterResource(id = R.drawable.cover_empty_), contentDescription = ""
                    )
                    Column {
                        Text(text = listaLivros.firstOrNull()?.TITULO ?: "", fontWeight = FontWeight.W600)
                        Text(text = listaLivros.firstOrNull()?.AUTOR ?: "", fontWeight = FontWeight.W300)
                        Text(text = listaLivros.firstOrNull()?.ANO ?: "")
                    }
                }
            }

            items(listaLivros) { livro ->
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, start = 15.dp, bottom = 6.dp),
                    text = "Descrição:", fontWeight = FontWeight.W600
                )
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    fontWeight = FontWeight.W400,
                    text = livro.DESCRICAO
                )
            }
        }

        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dateTime = LocalDateTime.now()
        val data = dateTime.format(format)

        Button(
            modifier = Modifier
                .padding(bottom = 25.dp)
                .align(Alignment.BottomCenter)
                .width(180.dp),
            onClick = {
                livroViewModel.OperacaoCrud(
                    Dt_LivroReservado(
                        IDLIVRO = listaLivros.firstOrNull()?.ID_BOOK ?: 0,
                        IDUSUARIO = idusuario,
                        DATA_RESERVADO = data,
                        DATA_DEVOLUCAO = "29/09/2025"
                    )
                )
            }
        ) {
            Text("Reservar:", fontWeight = FontWeight.W700)
        }
    }

    BackHandler {
        livroViewModel.ResetNext()
        navController.popBackStack()
    }

    LaunchedEffect(uiState_) {
        if (uiState_ is ReservadoUiState.Sucesso) {
            livroViewModel.ResetNext()
            navController.popBackStack()
        }
    }
}