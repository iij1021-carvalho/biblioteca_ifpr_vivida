package com.example.ifpr_biblioteca.Model.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Data.LivroOperacao
import com.example.ifpr_biblioteca.Data.LivroUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel_Livro(private val api_rotas: Api = Api()) : ViewModel() {
    private val _uiState = MutableStateFlow<LivroUiState>(LivroUiState.Idle)

    private val _livros = MutableStateFlow<List<Dt_Book>>(emptyList())
    val livros: StateFlow<List<Dt_Book>> = _livros

    val uiState: StateFlow<LivroUiState> = _uiState

    fun executarOperacao(livro_operacao: LivroOperacao) {
        viewModelScope.launch {
            _uiState.value = LivroUiState.Loading
            val resultado = try {
                when (livro_operacao) {
                    is LivroOperacao.Novo -> api_rotas.api.registrarlivros(livro_operacao.livro).isSuccessful
                    is LivroOperacao.Editar -> api_rotas.api.editarlivro(livro_operacao.livro).isSuccessful
                    is LivroOperacao.Excluir -> api_rotas.api.deletarlivro(livro_operacao.livro).isSuccessful
                    else -> {}
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }

            _uiState.value = if (true) {
                LivroUiState.Sucess()
            } else {
                LivroUiState.Erro("falha ao executar")
            }
        }
    }
}