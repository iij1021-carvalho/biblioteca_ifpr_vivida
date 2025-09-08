package com.example.ifpr_biblioteca.Data

import kotlinx.serialization.Serializable

@Serializable
data class Dt_Categoria(
    val IDCATEGORIA: Int = 0,
    val DESCRICAO: String = "",
)

@Serializable
data class Dt_CategoriaResponse(
    val status: String = "",
    val message: String = "",
    val data: List<Dt_Categoria>
)

sealed class CategoriaOperacao {
    data class Adicionar(val categoria: Dt_Categoria) : CategoriaOperacao()
    data class Editar(val categoria: Dt_Categoria) : CategoriaOperacao()
    data class Excluir(val categoria: Dt_Categoria) : CategoriaOperacao()
}

sealed class CategoriaUiState {
    object idle : CategoriaUiState()
    object loading : CategoriaUiState()
    data class Sucess(val message: String = "Operação realizada com sucesso") : CategoriaUiState()
    data class Error(val error: String) : CategoriaUiState()
}