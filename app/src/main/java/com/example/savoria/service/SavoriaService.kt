package com.example.savoria.service

import com.example.savoria.model.APIResponse
import com.example.savoria.model.LoginResponse
import com.example.savoria.model.User
import com.example.savoria.model.UserDetails
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): Response <User>

    @GET("viewUser")
    suspend fun viewUser(@Header("Authorization") token: String): List <User>
    @GET("viewUserDetails")
    suspend fun viewUserDetails(@Header("Authorization") token: String, @Query("userid") id: Int): Response <UserDetails>

    // Recipe
    @Multipart
    @POST("createRecipe")
    suspend fun createRecipe(
        @Header("Authorization") token: String,
        @Part("recipe_name") recipe_name: RequestBody,
        @Part("caption") caption: RequestBody,
        @Part("ingredients") ingredients: RequestBody,
        @Part("steps") steps: RequestBody,
        @Part file: MultipartBody.Part,
        @Part("calorie") calorie: RequestBody,
        @Part("servings") servings: RequestBody,
        @Part("time") time: RequestBody,
        @Part("categories") categories: RequestBody,
    ): APIResponse
}