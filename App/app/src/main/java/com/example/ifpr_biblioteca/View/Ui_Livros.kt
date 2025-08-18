package com.example.ifpr_biblioteca.View

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifpr_biblioteca.Data.Dt_Menu
import com.example.ifpr_biblioteca.Data.RetornaMenu
import com.example.ifpr_biblioteca.R

@SuppressLint("ResourceAsColor")
@Preview
@Composable
fun ListaLivros() {
    var texto by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn {
            item {
                Row {
                    RenderizaMenu()
                    OutlinedTextField(
                        modifier = Modifier
                            .width(300.dp),
                        value = texto,
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "")
                        },
                        onValueChange = {
                            texto = it
                        },
                        label = { Text("Informe o nome do Livro:") }
                    )
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 10.dp, bottom = 10.dp),
                    text = "Adicionados recentemente"
                )

                LivrosAdicionadosRecentemente()

                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 10.dp, bottom = 10.dp),
                    text = "Livros"
                )

                LivrosDisponiveis()
            }
        }
    }
}

@Composable
fun LivrosAdicionadosRecentemente() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(3) {
            Card(
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .padding(end = 5.dp, start = 5.dp)
                    .size(width = 110.dp, height = 135.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pe), // Ex: R.drawable.capa_aventura
                    contentDescription = "Capa do livro",
                    modifier = Modifier
                        .fillMaxSize() // Ajuste o modificador conforme necessário
                )
            }
        }
    }
}

@Composable
fun LivrosDisponiveis() {
    repeat(3) { /// roda o laço da quantidade de livros disponiveis
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(3) { // fixado essse valor para manter o tamanho da tela
                    Card(
                        elevation = CardDefaults.cardElevation(2.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        modifier = Modifier
                            .padding(end = 5.dp, start = 5.dp)
                            .size(width = 110.dp, height = 135.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pe), // Ex: R.drawable.capa_aventura
                            contentDescription = "Capa do livro",
                            modifier = Modifier
                                .fillMaxSize() // Ajuste o modificador conforme necessário
                        )
                    }
                }
            }
        }
    }
}
