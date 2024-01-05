package com.example.savoria.ui.service

import com.example.savoria.ui.model.APIResponse
import com.example.savoria.ui.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface MyDBService {
    @POST("login")
    suspend fun login(@Body user: User):APIResponse

    @DELETE("logout")
    suspend fun logout(): APIResponse

    @POST("createUser")
    suspend fun register(@Body user: User): APIResponse
}