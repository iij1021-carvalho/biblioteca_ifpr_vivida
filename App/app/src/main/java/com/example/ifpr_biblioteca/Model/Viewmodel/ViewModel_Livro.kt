package com.example.ifpr_biblioteca.Model.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.Books_Paginacao
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Data.LivroOperacao
import com.example.ifpr_biblioteca.Data.LivroUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class ViewModel_Livro(private val api_rotas: Api = Api()) : ViewModel() {
    private val _uiState = MutableStateFlow<LivroUiState>(LivroUiState.Idle)
    private val _livros = MutableStateFlow<List<Dt_Book>>(emptyList())
    val livros: StateFlow<List<Dt_Book>> = _livros
    val uiState: StateFlow<LivroUiState> = _uiState

    private var _livro = MutableStateFlow<List<Dt_Book>>(emptyList())
    val livro: StateFlow<List<Dt_Book>> = _livro

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

    fun retornalivros(book: Books_Paginacao) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val resposta = api_rotas.api.retornalivropaginacao(book)
                if (resposta.isSuccessful) {
                    _livro.value = _livro.value + resposta.body()?.data!!
                } else {
                    _livro.value = emptyList()
                }
            } catch (
                e: Exception
            ) {
                _livro.value = emptyList()
            }
        }
    }

    fun buscaLivroTitulo(book: Dt_Book) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val resposta = api_rotas.api.buscalivroTitulo(book)
                if (resposta.isSuccessful) {
                    _livro.value = resposta.body()?.data!!
                }else {
                    _livro.value = emptyList()
                }
            } catch (e: Exception) {

            }
        }
    }
}