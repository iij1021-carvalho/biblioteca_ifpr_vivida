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
    private var _iduser: MutableStateFlow<Int> = MutableStateFlow(0)
    val uiState: StateFlow<UsuarioUiState> = _uistate
    val iduser: StateFlow<Int> = _iduser

    fun executarOperacao(usuario_operacao: UsuarioOperacao) {
        viewModelScope.launch {
            _uistate.value = UsuarioUiState.Loading
            try {
                when (usuario_operacao) {
                    is UsuarioOperacao.Novo -> {
                        api_rotas.api.registrarusuario(usuario_operacao.usuario).isSuccessful
                        _uistate.value = UsuarioUiState.Sucess("Dados registrados com sucesso")
                    }

                    is UsuarioOperacao.Excluir -> {
                        api_rotas.api.deletarusuario(usuario_operacao.usuario).isSuccessful
                        _uistate.value = UsuarioUiState.Sucess("Dados deletados com sucesso")
                    }

                    is UsuarioOperacao.Editar -> {
                        api_rotas.api.editarusuario(usuario_operacao.usuario).isSuccessful
                        _uistate.value = UsuarioUiState.Sucess("Dados editados com sucesso")
                    }

                    is UsuarioOperacao.Entrar -> {
                        val response = api_rotas.api.efectuarentradausuario(usuario_operacao.usuario).body()
                        _iduser.value = response?.data?.firstOrNull()?.ID_USUARIO!!
                        _uistate.value = UsuarioUiState.Sucess("Dados obtidos com sucesso")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uistate.value = UsuarioUiState.Erro("Falha")
            }
        }
    }
}