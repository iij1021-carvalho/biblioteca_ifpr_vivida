package com.example.ifpr_biblioteca.Model.Viewmodel

import com.example.ifpr_biblioteca.Data.Book_Response
import com.example.ifpr_biblioteca.Data.Books
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface Rotas {
   @POST("registrarlivro")
   suspend fun registrarlivros(@Body books: Books) : retrofit2.Response<Books>

   @PUT("editarlivro")
   suspend fun editarlivro(@Body books: Books) : retrofit2.Response<Books>

   @POST("deletarlivro")
   suspend fun deletarlivro(@Body books: Books) : retrofit2.Response<Books>
}