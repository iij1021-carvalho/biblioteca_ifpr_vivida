package com.example.ifpr_biblioteca.Data

data class Dt_Menu(
    val descricao: String
)

fun RetornaMenu(): List<Dt_Menu> {
    var lista = listOf(
        Dt_Menu(
            descricao = "Categorias"
        ),
        Dt_Menu(
            descricao = "Meus livros"
        ),
        Dt_Menu(
            descricao = "Livros"
        ),
        Dt_Menu(
            descricao = "Cadastrar livro"
        ),
        Dt_Menu(
            descricao = "Inventario"
        ),
        Dt_Menu(
            descricao = "Registrar localizacao"
        )
    )
    return lista
}