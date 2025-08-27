package com.example.ifpr_biblioteca.Data

import kotlinx.serialization.Serializable

@Serializable
data class Dt_Livro(
    val ID: Int = 0,
    val TITULO: String = "",
    val AUTOR: String = "",
    val ANO: String = "",
    val QRCODE: String = "",
    val STATUS: String = "",
    val ID_CATEGORIA: Int = 0,
    val ID_LOCALIZACAO: Int = 0,
    val PASTA_CAPA: String = ""
)

data class Dt_LivrosResponse(
    val status: String,
    val message: String,
    val data: List<Dt_Livro>
)

sealed class LivroOperacao {
    data class Novo(val livro: Dt_Livro) : LivroOperacao()
    data class Editar(val livro: Dt_Livro) : LivroOperacao()
    data class Excluir(val livro: Dt_Livro) : LivroOperacao()
}

sealed class LivroUiState {
    object Idle : LivroUiState()
    object Loading : LivroUiState()
    data class Sucess(val message: String = "operação realizada com sucesso") : LivroUiState()
    data class Erro(val erro: String) : LivroUiState()
}