package com.example.ifpr_biblioteca.Data

import kotlinx.serialization.Serializable

@Serializable
data class Dt_Usuario(
    val ID_USUARIO: Int = 0,
    val NOME_USUARIO: String = "",
    val SENHA_USUARIO: String = "",
    val EMAIL_USUARIO: String = ""
)

@Serializable
data class Dt_UsuarioResponse(
    val status: String,
    val message: String,
    val data: List<Dt_Usuario>
)

sealed class UsuarioOperacao {
    data class Novo(val usuario: Dt_Usuario) : UsuarioOperacao()
    data class Editar(val usuario: Dt_Usuario) : UsuarioOperacao()
    data class Excluir(val usuario: Dt_Usuario) : UsuarioOperacao()
    data class Entrar(val usuario: Dt_Usuario) : UsuarioOperacao()
}

sealed class UsuarioUiState {
    object Idle : UsuarioUiState()
    object Loading : UsuarioUiState()
    data class Sucess(val message: String = "Operação realizada com sucesso") : UsuarioUiState()
    data class Erro(val erro: String) : UsuarioUiState()
}