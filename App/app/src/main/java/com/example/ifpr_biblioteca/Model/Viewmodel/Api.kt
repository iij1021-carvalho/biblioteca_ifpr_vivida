package com.example.ifpr_biblioteca.Model.Viewmodel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    val api: Rotas by lazy {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Rotas::class.java)
    }
}
