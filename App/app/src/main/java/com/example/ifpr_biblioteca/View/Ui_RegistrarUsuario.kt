package com.example.ifpr_biblioteca.View


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("ResourceAsColor")
@Preview
@Composable
fun RegistrarUsuario() {
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
                leadingIcon = {
                    Icon(
                        Icons.Default.Person, contentDescription = ""
                    )
                },
                onValueChange = {},
                label = { Text("Usuario:") }
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(370.dp),
                value = "",
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "")
                },
                onValueChange = {},
                label = { Text("Senha:") }
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(370.dp),
                value = "",
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "")
                },
                onValueChange = {},
                label = { Text("Email:") }
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(370.dp),
                value = "",
                leadingIcon = {
                    Icon(Icons.Default.DateRange, contentDescription = "")
                },
                onValueChange = {

                },
                label = { Text("Data Nascimento:") }
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
            onClick = {}
        ) {
            Text("Registrar usuario:")
        }
    }
}