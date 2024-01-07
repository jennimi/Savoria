package com.example.savoria.service

import com.example.savoria.model.APIResponse
import com.example.savoria.model.Comment
import com.example.savoria.model.LoginResponse
import com.example.savoria.model.Recipe
import com.example.savoria.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SavoriaService {

    // Auth
    @POST("login")
    suspend fun login(@Body user: User): LoginResponse
    @GET("logout")
    suspend fun logout(@Header("Authorization") token: String)
    @POST("createUser")
    suspend fun register(@Body user: User): APIResponse

    // User
    @GET("viewUser")
    suspend fun viewUser(@Header("Authorization") token: String): User
    @GET("viewUserDetails")
    suspend fun viewUserDetails(@Query("id") id: Int): List<User>
}