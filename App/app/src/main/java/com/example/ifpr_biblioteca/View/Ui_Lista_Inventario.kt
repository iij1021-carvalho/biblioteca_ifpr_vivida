package com.example.ifpr_biblioteca.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Model.Viewmodel.viewmodel_inventario

@Composable
fun ListaInventario(navController: NavController, viewmodelInventario: viewmodel_inventario) {
    val listaLivros by viewmodelInventario.livro.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 21.dp, top = 35.dp, end = 21.dp)
        ) {
            item {
                HorizontalDivider(thickness = 1.dp)
                Row {
                    Text(
                        modifier = Modifier
                            .width(120.dp),
                        text = "Autor",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(120.dp),
                        text = "Titulo",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif

                    )

                    Spacer(
                        modifier = Modifier
                            .width(15.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(90.dp),
                        text = "Quantidade",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                }
                HorizontalDivider(thickness = 1.dp)
            }

            items(listaLivros) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .width(130.dp),
                        text = it.AUTOR,
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(180.dp),
                        text = it.TITULO,
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(
                        modifier = Modifier
                            .width(5.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(20.dp),
                        text = it.QUANTIDADE.toString(),
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }

        Button(
            onClick = {

            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(200.dp)
                .padding(16.dp)
        ) {
            Text("Exportar")
        }
    }
}
