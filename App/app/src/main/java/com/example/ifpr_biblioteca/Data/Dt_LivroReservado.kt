package com.example.ifpr_biblioteca.Data

import android.os.Message

data class Dt_LivroReservado (
    val IDRESERVA: Int = 0,
    val IDLIVRO: Int = 0,
    val IDUSUARIO: Int = 0,
    val DATA_RESERVADO: String = "",
    val DATA_DEVOLUCAO: String = ""
)

sealed class Crud{
    data class Novo(val livroReservado: Dt_LivroReservado) : Crud()
    data class Editar(val livroReservado: Dt_LivroReservado) : Crud()
}
sealed class ReservadoUiState{
    object idle : ReservadoUiState()
    object loading: ReservadoUiState()
    data class Sucesso(val message: String) : ReservadoUiState()
    data class Erro(val message: String) : ReservadoUiState()
}

