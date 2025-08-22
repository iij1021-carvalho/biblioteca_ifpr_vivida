package com.example.ifpr_biblioteca.View

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ifpr_biblioteca.Data.Books
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_Usuario
import com.example.ifpr_biblioteca.R
import kotlinx.coroutines.launch

@SuppressLint("ResourceAsColor")
@Composable
fun RegistrarLivro(navController: NavController, viewmodelUsuario: ViewModel_Usuario) {
    val corountinescope = rememberCoroutineScope()
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
                corountinescope.launch {
                    val booksList =
                        Books(
                            ID = 0,
                            TITULO = "Clean Code",
                            AUTOR = "Robert C. Martin",
                            ANO = "2008",
                            QRCODE = "QR123456",
                            STATUS = "D", // D = Disponível, E = Emprestado, etc
                            ID_CATEGORIA = 10,
                            ID_LOCALIZACAO = 5,
                            PASTA_CAPA = "images/clean_code.jpg"
                        )

                    if (viewmodelUsuario.OperacoesLivro(booksList, "N")) {
                        Log.d("dados", "Dados inseridos com sucesso")
                    } else {
                        Log.d("falha", "falha ao registrar")
                    }
                }
            }
        ) {
            Text("Cadastrar Livro:")
        }
    }
}