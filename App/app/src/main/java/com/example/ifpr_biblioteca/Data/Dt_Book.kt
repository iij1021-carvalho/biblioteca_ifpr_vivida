package com.example.ifpr_biblioteca.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dt_Book(
    @SerialName("ID_BOOK") val ID_BOOK: Int = 0,
    @SerialName("CODIGO_BARRA") val CODIGO_BARRA: Int = 0,
    @SerialName("AUTOR") val AUTOR: String = "",
    @SerialName("TITULO") val TITULO: String = "",
    @SerialName("ISBN") val ISBN: String = "",
    @SerialName("IDCATEGORIA") val IDCATEGORIA: Int = 0,
    @SerialName("QUANTIDADE") var QUANTIDADE: Int = 0,
    @SerialName("DESCRICAO") var DESCRICAO: String = "",
    @SerialName("IDIOMA") var IDIOMA: String = "",
    @SerialName("ANO") var ANO: String = "",
    @SerialName("CAPA") var CAPA: String = ""
)

data class Dt_LivrosResponse(
    val status: String,
    val message: String,
    val data: List<Dt_Book>
)
data class Books_Paginacao(
    val INICIAL: Int = 0,
    val FINAL: Int = 0
)

sealed class LivroOperacao {
    data class Novo(val livro: Dt_Book) : LivroOperacao()
    data class Editar(val livro: Dt_Book) : LivroOperacao()
    data class Excluir(val livro: Dt_Book) : LivroOperacao()
    data class Dados(val livro: Dt_Book) : LivroOperacao()
}

sealed class LivroUiState {
    object Idle : LivroUiState()
    object Loading : LivroUiState()
    data class Sucess(val message: String = "operação realizada com sucesso") : LivroUiState()
    data class Erro(val erro: String) : LivroUiState()
}