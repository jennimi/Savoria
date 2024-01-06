package com.example.savoria.repository

import com.example.savoria.service.SavoriaService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SavoriaContainer() {
    companion object{
        val BASE_IMG = ""
        var ACCESS_TOKEN = ""

        private val BASE_URL = "http://10.0.2.2:8000/api/"

//    private val client = OkHttpClient.Builder()
//        .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
//        .build()
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: SavoriaService by lazy{
        retrofit.create(SavoriaService::class.java)
    }

    val SavoriaRepositories: SavoriaRepositories by lazy{
        SavoriaRepositories(retrofitService)
    }
}