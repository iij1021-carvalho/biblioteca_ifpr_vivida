package com.example.ifpr_biblioteca.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifpr_biblioteca.R

@Preview
@Composable
fun InventarioBiblioteca() {
    var texto by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Row {
                    RenderizaMenu()
                }
            }

            item {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                        .size(250.dp),
                    painter = painterResource(id = R.drawable.ic_biblioteca_background),
                    contentDescription = ""
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 21.dp, top = 20.dp)
                ) {
                    Text(fontWeight = FontWeight.Bold, text = "Quantidade:")
                    Text(
                        modifier = Modifier
                            .width(100.dp)
                            .padding(start = 70.dp),
                        text = "1"
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 21.dp, top = 20.dp)
                ) {
                    Text(fontWeight = FontWeight.Bold, text = "Total livros:")
                    Text(
                        modifier = Modifier
                            .width(100.dp)
                            .padding(start = 75.dp),
                        text = "1"
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 21.dp, top = 20.dp)
                ) {
                    Text(fontWeight = FontWeight.Bold, text = "Emprestado:")
                    Text(
                        modifier = Modifier
                            .width(100.dp)
                            .padding(start = 70.dp),
                        text = "1"
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
        ) {
            Button(
                modifier = Modifier
                    .padding(25.dp)
                    .width(150.dp),
                onClick = {}
            ) {
                Text("Pesquisar:")
            }

            Button(
                modifier = Modifier
                    .padding(25.dp)
                    .width(150.dp),
                onClick = {}
            ) {
                Text("Exportar:")
            }
        }
    }
}