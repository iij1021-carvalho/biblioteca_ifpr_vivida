package com.example.ifpr_biblioteca.Model.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.Dt_Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class viewmodel_inventario(private val api_rotas: Api = Api()) : ViewModel() {
    private var _livro = MutableStateFlow<List<Dt_Book>>(emptyList())
    val livro: StateFlow<List<Dt_Book>> = _livro

    fun EscanearQrcode(book: Dt_Book) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api_rotas.api.buscarlivroQrCode(book)

                if (response.isSuccessful) {
                    _livro.value = _livro.value + response.body()?.data!!
                } else {
                    _livro.value = emptyList()
                }
            } catch (e: Exception) {
                _livro.value = emptyList()
            }
        }
    }

    fun AtualizarQuantidade(idlivro: Int, qtd: Int) {
        _livro.update { current ->
            current.map {
                if (it.IDBOOK == idlivro) {
                    it.copy(QUANTIDADE = qtd)
                } else {
                    it
                }
            }
        }
    }
}