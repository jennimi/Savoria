package com.example.savoria.repository

import com.example.savoria.service.MyDBService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyDBContainer() {
    companion object{
        val BASE_IMG = ""
        var ACCESS_TOKEN = ""

        private val BASE_URL = "http://10.0.2.2:8000/api/"

//    private val client = OkHttpClient.Builder()
//        .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
//        .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        private val retrofitService: MyDBService by lazy{
            retrofit.create(MyDBService::class.java)
        }

        val myDBRepositories: MyDBRepositories by lazy {
            // Create an instance of MyDBRepositories with the retrofitService
            MyDBRepositories(retrofitService)
        }
    }

    private val BASE_URL = "http://10.0.2.2:8000/api/"

//    private val client = OkHttpClient.Builder()
//        .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
//        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: MyDBService by lazy{
        retrofit.create(MyDBService::class.java)
    }

    val myDBRepositories: MyDBRepositories by lazy{
        MyDBRepositories(retrofitService)
    }
}