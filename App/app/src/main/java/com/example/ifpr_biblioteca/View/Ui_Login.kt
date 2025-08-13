package com.example.ifpr_biblioteca.View


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifpr_biblioteca.R

@Preview
@Composable
fun EntradaUsuario() {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ) {
        Column(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier.size(300.dp),
                painter = painterResource(id = R.drawable.biblioteca), contentDescription = ""
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(330.dp),
                value = usuario,
                leadingIcon = {
                    Icon(
                        Icons.Default.Person, contentDescription = ""
                    )
                },
                onValueChange = {
                    usuario = it
                },
                label = { Text("Usuario:") }
            )

            Spacer(
                modifier = Modifier
                    .height(15.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(330.dp),
                value = senha,
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                onValueChange = {
                    senha = it
                },
                label = { Text("Senha:") }
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            Button(
                modifier = Modifier
                    .width(330.dp),
                onClick = {}
            ) {
                Text("Conectar-se")
            }

            Text(
                modifier = Modifier
                    .clickable() {

                    },
                text = "Esqueceu a senha?",
                textDecoration = TextDecoration.Underline
            )

            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )

            Row {
                Text(
                    modifier = Modifier
                        .clickable() {

                        },
                    text = "Você ainda não possui uma conta?"
                )

                Text(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable() {

                        },
                    text = "Crie uma agora",
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}