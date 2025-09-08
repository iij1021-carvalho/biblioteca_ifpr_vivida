package com.example.ifpr_biblioteca.Model.Viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.CategoriaUiState
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Data.Dt_LivrosResponse
import com.example.ifpr_biblioteca.Data.LivroOperacao
import com.example.ifpr_biblioteca.Data.LivroUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel_Inventario(private val api_rotas: Api = Api()) : ViewModel() {
    private val _uiState = MutableStateFlow<LivroUiState>(LivroUiState.Idle)
    val uiState: StateFlow<LivroUiState> = _uiState

    private var _livros = MutableStateFlow<List<Dt_LivrosResponse>>(emptyList())
    val livros: StateFlow<List<Dt_LivrosResponse>> = _livros

    fun RetornaDadosQrCode(book: Dt_Book){
        viewModelScope.launch {
            _livros = (api_rotas.api.buscarlivroQrCode(book).body()?.data ?: emptyList() ) as MutableStateFlow<List<Dt_LivrosResponse>>
        }
    }
}