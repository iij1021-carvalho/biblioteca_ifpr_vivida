package com.example.ifpr_biblioteca.Model.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.UsuarioOperacao
import com.example.ifpr_biblioteca.Data.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelUsuario(private val api_rotas: Api = Api()) : ViewModel() {
    private val _uistate = MutableStateFlow<UsuarioUiState>(UsuarioUiState.Idle)
    val uiState: StateFlow<UsuarioUiState> = _uistate

    fun executarOperacao(usuario_operacao: UsuarioOperacao) {
        viewModelScope.launch {
            _uistate.value = UsuarioUiState.Loading

            val resultado = try {
                when (usuario_operacao) {
                    is UsuarioOperacao.Novo -> api_rotas.api.registrarusuario(usuario_operacao.usuario).isSuccessful
                    is UsuarioOperacao.Excluir -> api_rotas.api.deletarusuario(usuario_operacao.usuario).isSuccessful
                    is UsuarioOperacao.Editar -> api_rotas.api.editarusuario(usuario_operacao.usuario).isSuccessful
                    is UsuarioOperacao.Entrar -> api_rotas.api.efectuarentradausuario(usuario_operacao.usuario).isSuccessful
                    else -> {
                        false
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }

            _uistate.value = if (resultado) {
                UsuarioUiState.Sucess()
            } else {
                UsuarioUiState.Erro("falha ao executar operacao")
            }
        }
    }
}