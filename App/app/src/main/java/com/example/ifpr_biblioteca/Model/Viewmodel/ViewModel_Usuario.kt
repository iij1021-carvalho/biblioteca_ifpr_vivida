package com.example.ifpr_biblioteca.Model.Viewmodel

import androidx.lifecycle.ViewModel
import com.example.ifpr_biblioteca.Data.Books

class ViewModel_Usuario : ViewModel() {
    val api_rotas = Api()

    suspend fun OperacoesLivro(books: Books, tipo_operacao: String): Boolean {
        when (tipo_operacao) {
            "D" -> {
                return api_rotas.api.deletarlivro(books).isSuccessful
            }

            "N" -> {
                return api_rotas.api.deletarlivro(books).isSuccessful
            }

            "E" -> {
                return api_rotas.api.editarlivro(books).isSuccessful
            }
        }
        return false
    }
}