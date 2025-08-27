package com.example.ifpr_biblioteca.Model.Viewmodel

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.CategoriaUiState
import com.example.ifpr_biblioteca.Data.Dt_Livro
import com.example.ifpr_biblioteca.Data.LivroOperacao
import com.example.ifpr_biblioteca.Data.LivroUiState
import com.example.ifpr_biblioteca.Data.UsuarioOperacao
import com.example.ifpr_biblioteca.Data.UsuarioUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel_Livro(private val api_rotas: Api = Api()) : ViewModel() {
    private val _uiState = MutableStateFlow<LivroUiState>(LivroUiState.Idle)
    val uiState: StateFlow<LivroUiState> = _uiState

    fun executarOperacao(livro_operacao: LivroOperacao) {
        viewModelScope.launch {
            _uiState.value = LivroUiState.Loading

            val resultado = try {
                when (livro_operacao) {
                    is LivroOperacao.Novo -> {
                        api_rotas.api.registrarlivros(livro_operacao.livro).isSuccessful
                    }

                    is LivroOperacao.Editar -> {
                        api_rotas.api.editarlivro(livro_operacao.livro).isSuccessful
                    }

                    is LivroOperacao.Excluir -> {
                        api_rotas.api.deletarlivro(livro_operacao.livro).isSuccessful
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }

            _uiState.value = if (resultado) {
                LivroUiState.Sucess()
            } else {
                LivroUiState.Erro("falha ao executar")
            }
        }
    }
}