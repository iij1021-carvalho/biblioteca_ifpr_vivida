package com.example.ifpr_biblioteca.Model.Viewmodel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class Api {
    val url = "http://172.16.144.101:3000"
    val api: Rotas by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Rotas::class.java)
    }
}
