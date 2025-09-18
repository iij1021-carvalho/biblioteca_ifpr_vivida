package com.example.ifpr_biblioteca.Model.Viewmodel

import com.example.ifpr_biblioteca.Data.Books_Paginacao
import com.example.ifpr_biblioteca.Data.Dt_Categoria
import com.example.ifpr_biblioteca.Data.Dt_CategoriaResponse
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Data.Dt_LivrosResponse
import com.example.ifpr_biblioteca.Data.Dt_Usuario
import com.example.ifpr_biblioteca.Data.Dt_UsuarioResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface Rotas {
    @POST("registrarlivro")
    suspend fun registrarlivros(@Body books: Dt_Book): retrofit2.Response<Dt_Book>

    @PUT("editarlivro")
    suspend fun editarlivro(@Body books: Dt_Book): retrofit2.Response<Dt_Book>

    @POST("deletarlivro")
    suspend fun deletarlivro(@Body books: Dt_Book): retrofit2.Response<Dt_Book>

    @POST("retornalivrospaginacao")
    suspend fun retornalivropaginacao(@Body books: Books_Paginacao): retrofit2.Response<Dt_LivrosResponse>

    @POST("buscalivrotitulo")
    suspend fun buscalivroTitulo(@Body books: Dt_Book): retrofit2.Response<Dt_LivrosResponse>

    @POST("buscarlivro")
    suspend fun buscarlivroQrCode(@Body books: Dt_Book): retrofit2.Response<Dt_LivrosResponse>

    @POST("registrarusuario")
    suspend fun registrarusuario(@Body usuario: Dt_Usuario): retrofit2.Response<Dt_Usuario>

    @PUT("editarusuario")
    suspend fun editarusuario(@Body usuario: Dt_Usuario): retrofit2.Response<Dt_Usuario>

    @POST("deletarusuario")
    suspend fun deletarusuario(@Body usuario: Dt_Usuario): retrofit2.Response<Dt_Usuario>

    @POST("efectuarentradausuario")
    suspend fun efectuarentradausuario(@Body usuario: Dt_Usuario): retrofit2.Response<Dt_UsuarioResponse>

    @GET("retornatodosusuario")
    suspend fun retornatodosusuario(): retrofit2.Response<Dt_UsuarioResponse>

    @POST("registrarcategoria")
    suspend fun registrarcategoria(@Body categoria: Dt_Categoria): retrofit2.Response<Dt_Categoria>

    @PUT("editarcategoria")
    suspend fun editarcategoria(@Body categoria: Dt_Categoria): retrofit2.Response<Dt_Categoria>

    @POST("deletarcategoria")
    suspend fun deletarcategoria(@Body categoria: Dt_Categoria): retrofit2.Response<Dt_Categoria>

    @GET("retornartodascategoria")
    suspend fun retornatodascategoria(): retrofit2.Response<Dt_CategoriaResponse>
}