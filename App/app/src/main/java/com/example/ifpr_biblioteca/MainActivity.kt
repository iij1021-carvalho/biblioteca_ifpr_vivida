package com.example.ifpr_biblioteca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModelUsuario
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_Livro
import com.example.ifpr_biblioteca.Model.Viewmodel.viewmodel_inventario
import com.example.ifpr_biblioteca.View.BookListScreen
import com.example.ifpr_biblioteca.View.EntradaUsuario
import com.example.ifpr_biblioteca.View.ListaInventario
import com.example.ifpr_biblioteca.View.ListaLivros
import com.example.ifpr_biblioteca.View.RegistrarLivro
import com.example.ifpr_biblioteca.View.RegistrarUsuario
import com.example.ifpr_biblioteca.ui.theme.Ifpr_bibliotecaTheme

class MainActivity : ComponentActivity() {
    private val inventarioViewModel: viewmodel_inventario by viewModels()
    private val livroViewModel: ViewModel_Livro by viewModels()
    private val usuarioViewModel: ViewModelUsuario by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ifpr_bibliotecaTheme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "ListaLivros") {
                    composable("login") {
                        EntradaUsuario(nav, usuarioViewModel)
                    }

                    composable("RegistrarUsuario") {
                        RegistrarUsuario(nav, usuarioViewModel)
                    }

                    composable("registrarlivro") {
                        RegistrarLivro(nav, livroViewModel)
                    }

                    composable("Inventario") {
                        BookListScreen(nav, inventarioViewModel)
                    }

                    composable("ListaInventario") {
                        ListaInventario(nav, inventarioViewModel)
                    }

                    composable("ListaLivros"){
                        ListaLivros(nav, livroViewModel)
                    }
                }
            }
        }
    }
}
