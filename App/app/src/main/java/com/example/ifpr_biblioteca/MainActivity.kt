package com.example.ifpr_biblioteca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_Usuario
import com.example.ifpr_biblioteca.View.RegistrarLivro
import com.example.ifpr_biblioteca.ui.theme.Ifpr_bibliotecaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ifpr_bibliotecaTheme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "registrarlivro") {
                    composable("registrarlivro") {
                        val viewmodel_usuario: ViewModel_Usuario = viewModel()
                        RegistrarLivro(nav, viewmodel_usuario)
                    }
                }
            }
        }
    }
}
