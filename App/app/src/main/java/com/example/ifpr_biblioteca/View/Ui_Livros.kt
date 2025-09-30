package com.example.ifpr_biblioteca.View

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ifpr_biblioteca.Data.Books_Paginacao
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Data.LivroUiState
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_Livro

@Composable
fun ListaLivros(navController: NavController, viewmodelLivro: ViewModel_Livro) {
    var texto by remember { mutableStateOf("") }
    val listaLivros by viewmodelLivro.livro.collectAsState()
    val next by viewmodelLivro.next.collectAsState()
    val listState = rememberLazyListState()
    val uiState by viewmodelLivro.uiState.collectAsState()

    Box(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier,
            state = listState
        ) {
            item {
                Row {
                    RenderizaMenu(navController)
                    OutlinedTextField(
                        modifier = Modifier
                            .width(320.dp),
                        value = texto,
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "")
                        },
                        onValueChange = {
                            texto = it
                            viewmodelLivro.buscaLivroTitulo(
                                Dt_Book(
                                    TITULO = it
                                )
                            )
                        },
                        label = { Text("Informe o nome do Livro:") }
                    )
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 10.dp, bottom = 10.dp),
                    text = "Adicionados recentemente", fontWeight = FontWeight.W700
                )

                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 10.dp, bottom = 10.dp),
                    text = "Livros", fontWeight = FontWeight.W700
                )
            }

            items(listaLivros.chunked(2)) { livro ->
                if(livro.size > 1) {
                    LivrosDisponiveis(livro, viewmodelLivro)
                }
            }
        }

        if (next) {
            Loading(viewmodelLivro)
        }

        if (texto.isEmpty() && !next) {
            LaunchedEffect(listState) {
                snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                    .collect { lastVisibleItemIndex ->
                        if (lastVisibleItemIndex != null && lastVisibleItemIndex == listState.layoutInfo.totalItemsCount - 1) {
                            viewmodelLivro.retornalivros(
                                Books_Paginacao(
                                    INICIAL = listaLivros.size,
                                    FINAL = 10
                                )
                            )
                        }
                    }
            }
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is LivroUiState.Sucess) {
            navController.navigate("ReservarLivro")
            viewmodelLivro.ResetUi()
        } else if (uiState is LivroUiState.Erro) {
            viewmodelLivro.ResetNext()
        }
    }
}

@Composable
fun LivrosAdicionadosRecentemente(viewmodelLivro: ViewModel_Livro, Dtlivro: List<Dt_Book>) {
    val listState = rememberLazyListState()
    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(Dtlivro) { livro ->
            Card(
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .padding(end = 5.dp, start = 5.dp)
                    .size(width = 150.dp, height = 135.dp)
            ) {
                Column {
                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp),
                        text = livro.TITULO, fontWeight = FontWeight.W600,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Composable
fun LivrosDisponiveis(livro: List<Dt_Book>, viewmodelLivro: ViewModel_Livro) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        livro.forEach { livro ->
            Card(
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .weight(1f)
                    .width(180.dp)
                    .padding(end = 5.dp, start = 5.dp)
                    .height(135.dp)
                    .clickable() {
                        viewmodelLivro.BuscaLivroGoogle(livro)
                        viewmodelLivro.HabilitaNext()
                    }
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        livro.TITULO, fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text("Autor: ${livro.AUTOR}", color = Color.Gray, fontSize = 11.sp)
                }
            }
        }
    }
}

@Composable
fun Loading(viewmodelLivro: ViewModel_Livro) {
    val uiState by viewmodelLivro.uiState.collectAsState()
    val context = LocalContext.current

    if (uiState is LivroUiState.Loading) {
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
        if (uiState is LivroUiState.Erro) {
            Toast.makeText(context, "Falha ao localizar informações do livro", Toast.LENGTH_SHORT).show()
        }
    }
}
