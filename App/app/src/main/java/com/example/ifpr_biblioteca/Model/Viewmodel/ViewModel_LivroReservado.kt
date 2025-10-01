package com.example.ifpr_biblioteca.Model.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.Dt_LivroReservado
import com.example.ifpr_biblioteca.Data.ReservadoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel_LivroReservado(private val api: Api = Api()) : ViewModel() {
    private var _lista = MutableStateFlow<List<Dt_LivroReservado>>(emptyList())
    val lista: StateFlow<List<Dt_LivroReservado>> = _lista
    private val _Ui = MutableStateFlow<ReservadoUiState>(ReservadoUiState.idle)
    val Ui: StateFlow<ReservadoUiState> = _Ui

    fun RetornaLivrosReservados(reservado: Dt_LivroReservado) {
        viewModelScope.launch {
            try {
                _Ui.value = ReservadoUiState.loading
                val resposta = api.api.Listalivroreservado(reservado)
                if (resposta.isSuccessful) {
                    _lista.value = resposta.body()?.data!!
                    _Ui.value = ReservadoUiState.Sucesso("")
                } else {
                    _Ui.value = ReservadoUiState.Erro("")
                }
            } catch (e: Exception) {
                _Ui.value = ReservadoUiState.Erro("")
            }
        }
    }
}