package com.example.ifpr_biblioteca.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ifpr_biblioteca.Data.Dt_LivroReservado
import com.example.ifpr_biblioteca.Data.ReservadoUiState
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModelUsuario
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_LivroReservado

@Composable
fun ListaReservado(navController: NavController, viewmodelLivroreservado: ViewModel_LivroReservado, usuarioViewModel: ViewModelUsuario) {
    val dados by viewmodelLivroreservado.lista.collectAsState()
    val ui by viewmodelLivroreservado.Ui.collectAsState()
    val idusuario by usuarioViewModel.iduser.collectAsState()

    viewmodelLivroreservado.RetornaLivrosReservados(
        Dt_LivroReservado(
            IDUSUARIO = idusuario
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        if (ui is ReservadoUiState.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.Black,
                    strokeWidth = 4.dp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 21.dp)
            ) {
                items(dados) { lista ->
                    Row {
                        AsyncImage(
                            model = "https://picsum.photos/300/200" ?: com.example.ifpr_biblioteca.R.drawable.cover_empty_,
                            contentDescription = "Imagem de exemplo",
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .size(150.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 15.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(text = lista.TITULO, fontWeight = FontWeight.W700, fontSize = 15.sp)
                            Row() {
                                Text(text = "Autor ", fontWeight = FontWeight.W700, fontSize = 10.sp)
                                Text(text = lista.AUTOR, fontWeight = FontWeight.W200, fontSize = 10.sp)
                            }
                            Row() {
                                Text(text = "Ano ", fontWeight = FontWeight.W700, fontSize = 10.sp)
                                Text(text = lista.DATA_RESERVADO, fontWeight = FontWeight.W100, fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}