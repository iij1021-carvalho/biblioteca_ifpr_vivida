package com.example.ifpr_biblioteca

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModelUsuario
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_Inventario
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_Livro
import com.example.ifpr_biblioteca.View.EntradaUsuario
import com.example.ifpr_biblioteca.View.Inventario
import com.example.ifpr_biblioteca.View.ListaLivros
import com.example.ifpr_biblioteca.View.RegistrarLivro
import com.example.ifpr_biblioteca.View.RegistrarUsuario
import com.example.ifpr_biblioteca.ui.theme.Ifpr_bibliotecaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ifpr_bibliotecaTheme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "login") {
                    composable("login") {
                        val viewModelUsuario: ViewModelUsuario = viewModel()
                        EntradaUsuario(nav, viewModelUsuario)
                    }

                    composable("RegistrarUsuario") {
                        val viewModelUsuario: ViewModelUsuario = viewModel()
                        RegistrarUsuario(nav,viewModelUsuario)
                    }

                    composable("ListaLivros") {
                        ListaLivros(nav)
                    }

                    composable("registrarlivro"){
                        val viewmodelLivro: ViewModel_Livro = viewModel()
                        RegistrarLivro(nav,viewmodelLivro)
                    }

                    composable("Inventario"){
                        val viewmodelInventario: ViewModel_Inventario = viewModel()
                        Inventario(nav,viewmodelInventario)
                    }
                }
            }
        }
    }
}
