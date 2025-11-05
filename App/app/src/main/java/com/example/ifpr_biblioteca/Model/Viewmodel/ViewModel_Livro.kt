package com.example.ifpr_biblioteca.Model.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.Books_Paginacao
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Data.Dt_LivroReservado
import com.example.ifpr_biblioteca.Data.LivroOperacao
import com.example.ifpr_biblioteca.Data.LivroUiState
import com.example.ifpr_biblioteca.Data.ReservadoUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel_Livro(private val api_rotas: Api = Api()) : ViewModel() {
    private val _uiState = MutableStateFlow<LivroUiState>(LivroUiState.Idle)
    val uiState: StateFlow<LivroUiState> = _uiState
    private var _livro = MutableStateFlow<List<Dt_Book>>(emptyList())
    val livro: StateFlow<List<Dt_Book>> = _livro
    private var _next: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val next: StateFlow<Boolean> = _next
    private val _uiState_ = MutableStateFlow<ReservadoUiState>(ReservadoUiState.idle)
    val uiState_: StateFlow<ReservadoUiState> = _uiState_

    fun executarOperacao(livro_operacao: LivroOperacao) {
        viewModelScope.launch {
            _uiState.value = LivroUiState.Loading
            try {
                when (livro_operacao) {
                    is LivroOperacao.Novo -> {
                        api_rotas.api.registrarlivros(livro_operacao.livro).isSuccessful
                        LivroUiState.Sucess()
                    }

                    is LivroOperacao.Editar -> {
                        api_rotas.api.editarlivro(livro_operacao.livro).isSuccessful
                        LivroUiState.Sucess()
                    }

                    is LivroOperacao.Excluir -> {
                        api_rotas.api.deletarlivro(livro_operacao.livro).isSuccessful
                        LivroUiState.Sucess()
                    }

                    else -> {}
                }
            } catch (e: Exception) {
                e.printStackTrace()
                LivroUiState.Erro("Erro ${e.message}")
            }
        }
    }

    fun BuscaLivroGoogle(book: Dt_Book) {
        viewModelScope.launch {
            _uiState.value = LivroUiState.Loading
            try {
                val resposta = api_rotas.api.BuscaLivroGoogle(book)
                if (resposta.isSuccessful) {
                    if(resposta.body()?.data != null) {
                        _livro.value = resposta.body()?.data ?: emptyList()
                        _uiState.value = LivroUiState.Sucess()
                    }else{
                        _uiState.value = LivroUiState.Erro("Falha ao carregar livros")
                    }
                } else {
                    _uiState.value = LivroUiState.Erro("Falha ao carregar livros")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = LivroUiState.Erro("Erro de conexão")
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
                } else {
                    _livro.value = emptyList()
                }
            } catch (e: Exception) {

            }
        }
    }
    fun ResetUi() {
        _uiState.value = LivroUiState.Idle
        _uiState_.value = ReservadoUiState.idle
    }
    fun ResetNext() {
        _next.value = false
    }
    fun HabilitaNext() {
        _next.value = true
    }
    fun OperacaoCrud(reservado: Dt_LivroReservado) {
        viewModelScope.launch {
            _uiState_.value = ReservadoUiState.loading
            try {
                val resultado = api_rotas.api.registrarreserva(reservado)
                if (resultado.isSuccessful) {
                    _uiState_.value = ReservadoUiState.Sucesso("Reserva registrada:")
                    _next.value = true
                } else {
                    _uiState_.value = ReservadoUiState.Erro("")
                    _next.value = false
                }
            } catch (e: Exception) {
                _uiState_.value = ReservadoUiState.Erro("")
                _next.value = false
                e.printStackTrace()
            }
        }
    }
}