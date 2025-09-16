package com.example.ifpr_biblioteca.Model.Viewmodel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    val api: Rotas by lazy {
        Retrofit.Builder()
            .baseUrl("http://172.16.145.118:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Rotas::class.java)
    }
}
