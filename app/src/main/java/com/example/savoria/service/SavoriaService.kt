package com.example.savoria.service

import com.example.savoria.model.APIResponse
import com.example.savoria.model.Comment
import com.example.savoria.model.Recipe
import com.example.savoria.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface SavoriaService {

    // Auth
    @POST("login")
    suspend fun login(@Body user: User): APIResponse
    @DELETE("logout")
    suspend fun logout(@Header("Authorization") token: String): APIResponse
    @POST("createUser")
    suspend fun register(@Body user: User): APIResponse

    // User
    @GET("viewUser")
    suspend fun viewUser(): APIResponse
    @GET("viewUserDetails")
    suspend fun viewUserDetails(@Query("id") userId:Int): APIResponse
    @PATCH("updateUser")
    suspend fun updateUser(@Body user: User): APIResponse
    @DELETE("deleteUser")
    suspend fun deleteUser(): APIResponse
    @POST("followUser")
    suspend fun followUser(): APIResponse
    @POST("unfollowUser")
    suspend fun unfollowUser(): APIResponse

    // Recipe
    @POST("createRecipe")
    suspend fun createRecipe(@Body recipe: Recipe): APIResponse
    @GET("viewRecipe")
    suspend fun viewRecipe(): APIResponse
    @GET("viewRecipeDetails")
    suspend fun viewRecipeDetails(): APIResponse
    @PATCH("updateRecipe")
    suspend fun updateRecipe(@Body recipe: Recipe): APIResponse
    @DELETE("deleteRecipe")
    suspend fun deleteRecipe(): APIResponse
    @POST("addFavorite")
    suspend fun addFavorite(): APIResponse
    @GET("viewFavorite")
    suspend fun viewFavorite(): APIResponse
    @DELETE("deleteFavorite")
    suspend fun deleteFavorite(): APIResponse

    // Comment
    @POST("createComment")
    suspend fun createComment(@Body comment: Comment): APIResponse
    @GET("viewUserComments")
    suspend fun viewUserComments(): APIResponse
    @DELETE("deleteComment")
    suspend fun deleteComment(): APIResponse
}