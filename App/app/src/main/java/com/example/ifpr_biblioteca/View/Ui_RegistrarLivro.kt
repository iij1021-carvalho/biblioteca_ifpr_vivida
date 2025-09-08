package com.example.ifpr_biblioteca.View

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Data.LivroOperacao.Novo
import com.example.ifpr_biblioteca.Data.LivroUiState
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_Livro
import com.example.ifpr_biblioteca.R

@SuppressLint("ResourceAsColor")
@Composable
fun RegistrarLivro(navController: NavController, viewmodelivro: ViewModel_Livro) {
    val uiState by viewmodelivro.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .width(370.dp),
                value = "",
                onValueChange = {},
                label = { Text("Titulo:") }
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(370.dp),
                value = "",
                onValueChange = {},
                label = { Text("Autor:") }
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(370.dp),
                value = "",
                onValueChange = {},
                label = { Text("Ano:") }
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(370.dp),
                value = "",
                onValueChange = {

                },
                label = { Text("QrCode:") }
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(370.dp),
                value = "",
                onValueChange = {

                },
                label = { Text("Categoria:") }
            )

            Image(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(300.dp),
                painter = painterResource(id = R.drawable.ic_biblioteca_background),
                contentDescription = ""
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 25.dp)
                .width(320.dp),
            onClick = {
                val booksList =
                    Dt_Book(
                        AUTOR = "Clean Code",
                        TITULO = "Clean Code programação",
                        ISBN = "012454545",
                        IDCATEGORIA = 869
                    )
                viewmodelivro.executarOperacao(Novo(booksList))
            }
        ) {
            if (uiState is LivroUiState.Loading) {
                CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
            } else {
                Text("Cadastrar Livro:")
            }
        }

        when (uiState) {
            is LivroUiState.Sucess -> {
                Text("✅ ${(uiState as LivroUiState.Sucess).message}")
                navController.navigate("ListaLivros")
            }

            is LivroUiState.Erro -> {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Text("❌ ${(uiState as LivroUiState.Erro).erro}")
                }
            }

            else -> {

            }
        }
    }
}