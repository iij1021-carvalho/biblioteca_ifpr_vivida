package com.example.ifpr_biblioteca.Model.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.CategoriaOperacao
import com.example.ifpr_biblioteca.Data.CategoriaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel_Categoria(private val api_rotas: Api = Api()) : ViewModel() {
    private val _uiState = MutableStateFlow<CategoriaUiState>(CategoriaUiState.idle)
    val uiState: StateFlow<CategoriaUiState> = _uiState

    fun executarOperacao(tipo_operacao: CategoriaOperacao) {
        viewModelScope.launch {
            _uiState.value = CategoriaUiState.loading

            val resultado = try {
                when (tipo_operacao) {
                    is CategoriaOperacao.Adicionar -> api_rotas.api.registrarcategoria(tipo_operacao.categoria).isSuccessful
                    is CategoriaOperacao.Editar -> api_rotas.api.editarcategoria(tipo_operacao.categoria).isSuccessful
                    is CategoriaOperacao.Excluir -> api_rotas.api.deletarcategoria(tipo_operacao.categoria).isSuccessful
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }

            _uiState.value = if (resultado) {
                CategoriaUiState.Sucess()
            } else {
                CategoriaUiState.Error("falha ao executar")
            }
        }
    }
}