package com.example.ifpr_biblioteca.Data

import kotlinx.serialization.Serializable

@Serializable
data class Books(
    val ID: Int = 0,
    val TITULO: String = "",
    val AUTOR: String = "",
    val ANO: String = "",
    val QRCODE: String = "",
    val STATUS: String = "",
    val ID_CATEGORIA: Int = 0,
    val ID_LOCALIZACAO: Int = 0,
    val PASTA_CAPA: String = ""
)

data class Book_Response(
    val status: String,
    val message: String,
    val data: List<Books>
)