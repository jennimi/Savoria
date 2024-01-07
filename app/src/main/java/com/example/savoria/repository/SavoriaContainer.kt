package com.example.savoria.repository

import com.example.savoria.model.User
import com.example.savoria.service.SavoriaService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient

class SavoriaContainer() {

    class AuthInterceptor(private val bearerToken: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder()
                .header("Authorization", "Bearer $bearerToken")
                .build()
            return chain.proceed(request)
        }
    }
    companion object{
        var ACCESS_TOKEN = ""
        var USER_ID = -1

//    private val client = OkHttpClient.Builder()
//        .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
//        .build()
    }
    private val BASE_URL = "http://10.0.2.2:8000/api/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: SavoriaService by lazy{
        retrofit.create(SavoriaService::class.java)
    }

    val SavoriaRepositories: SavoriaRepositories by lazy{
        SavoriaRepositories(retrofitService)
    }
}