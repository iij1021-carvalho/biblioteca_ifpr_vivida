package com.example.ifpr_biblioteca.Model.Viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Data.LivroUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class viewmodel_inventario(private val api_rotas: Api = Api()) : ViewModel() {
    private var _livro = MutableStateFlow<List<Dt_Book>>(emptyList())
    val livro: StateFlow<List<Dt_Book>> = _livro

    fun escanearQrcode(book: Dt_Book, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api_rotas.api.buscarlivroQrCode(book)
                if (response.isSuccessful) {
                    if (response.body()?.data == null) {
                        _livro.value = emptyList()
                    } else {
                        _livro.value += response.body()?.data!!
                    }
                } else {
                    _livro.value = emptyList()
                }
            } catch (e: Exception) {
                Log.d("Error", "Falha ao escanear o código ${e.message}")
            }
        }
    }

    fun deletarLivro(livro: Dt_Book) {
        _livro.value = _livro.value.filter { it != livro }
    }

    fun gerarArquivoPergamum(listaLivros: List<Dt_Book>, context: Context): File {
        val nomeArquivo = "inventario_pergamum_${System.currentTimeMillis()}.txt"
        val arquivo = File(context.getExternalFilesDir(null), nomeArquivo)
        arquivo.bufferedWriter(Charsets.UTF_8).use { writer ->
            listaLivros.forEach { codigo ->
                writer.write(codigo.CODIGO_BARRA.toString())
                writer.newLine()
            }
        }
        return arquivo
    }

    fun enviarWhatsApp(context: Context, arquivo: File) {
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            arquivo
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            `package` = "com.whatsapp" // garante que abra o WhatsApp
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_TEXT, "Segue o arquivo do inventário da biblioteca 📚")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.d("Error", "Falha ao enviar dados ao whatsapp ${e.message}")
        }
    }

    fun resetDados(){
        _livro.value = emptyList()
    }
}