package com.example.ifpr_biblioteca.View

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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservarLivro(navController: NavController, livroViewModel: ViewModel_Livro, usuarioViewModel: ViewModelUsuario) {
    val listaLivros by livroViewModel.livro.collectAsState()
    val uiState_ by livroViewModel.uiState_.collectAsState()
    val idusuario by usuarioViewModel.iduser.collectAsState()

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
            items(listaLivros) { livro ->
                Row {
                    Image(
                        modifier = Modifier.size(150.dp),
                        painter = painterResource(id = R.drawable.cover_empty_), contentDescription = ""
                    )
                    Column {
                        Text(text = livro.TITULO, fontWeight = FontWeight.W700)
                        Text(text = livro.AUTOR, fontWeight = FontWeight.W400)
                        Text(text = livro.ANO, fontWeight = FontWeight.W400)
                    }
                }
            }

            items(listaLivros) { livro ->
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 15.dp),
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

        Button(
            modifier = Modifier
                .padding(bottom = 25.dp)
                .align(Alignment.BottomCenter)
                .width(180.dp),
            onClick = {
                val reservado = Dt_LivroReservado(
                    IDLIVRO = listaLivros.firstOrNull()?.ID_BOOK ?: 0,
                    IDUSUARIO = idusuario,
                    DATA_RESERVADO = LocalDateTime.now().toString(),
                    DATA_DEVOLUCAO = "29/09/2025"
                )
                livroViewModel.OperacaoCrud(reservado)
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