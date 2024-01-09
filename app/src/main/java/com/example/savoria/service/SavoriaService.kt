package com.example.savoria.service

import com.example.savoria.model.APIResponse
import com.example.savoria.model.Category
import com.example.savoria.model.Comment
import com.example.savoria.model.LoginResponse
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.User
import com.example.savoria.model.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface SavoriaService {

    // Auth
    @POST("login")
    suspend fun login(@Body user: User): LoginResponse
    @GET("logout")
    suspend fun logout(@Header("Authorization") token: String)
    @Multipart
    @POST("createUser")
    suspend fun register(
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("name") name: RequestBody,
        @Part("birthdate") birthdate: RequestBody,
        @Part("description") description: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part file: MultipartBody.Part
    ): APIResponse

    // User
    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): Response <User>
    @GET("viewUserDetails/{id}")
    suspend fun viewUserDetails(@Header("Authorization") token: String, @Path("id") id: Int): Response <UserResponse>

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
    @GET("viewRecipe")
    suspend fun viewRecipe(@Header("Authorization") token: String): Response<List<RecipeResponse>>

    // Not sure it works
    @GET("viewUser")
    suspend fun viewUser(@Header("Authorization") token: String): Response<List<UserResponse>>
    @DELETE("deleteUser")
    suspend fun deleteUser(@Header("Authorization") token: String, @Query("id") id: Int): APIResponse
    @POST("followUser")
    suspend fun followUser(@Header("Authorization") token: String, @Query("id") id: Int): APIResponse
    @POST("unfollowUser")
    suspend fun unfollowUser(@Header("Authorization") token: String, @Query("id") id: Int): APIResponse

    @GET("viewRecipeDetails/{id}")
    suspend fun viewRecipeDetails(@Header("Authorization") token: String, @Path("id") id: Int): Response<RecipeResponse>
    @DELETE("deleteRecipe")
    suspend fun deleteRecipe(@Header("Authorization") token: String, @Query("id") id:Int): APIResponse
    @POST("addFavorite")
    suspend fun addFavorite(@Header("Authorization") token: String, @Query("id") id:Int): APIResponse
    @GET("viewFavorite")
    suspend fun viewFavorite(@Header("Authorization") token: String, @Query("id") id:Int): APIResponse
    // this one sends APIResponse?? harusnya Response<List<RecipeResponse>>
    @DELETE("deleteFavorite")
    suspend fun deleteFavorite(@Header("Authorization") token: String, @Query("id") id:Int): APIResponse

    @GET("viewCategory")
    suspend fun viewCategory(@Header("Authorization") token: String): Response<List<Category>>
    // still wrapped in "data"

    @POST("createComment")
    suspend fun createComment(
        @Header("Authorization") token: String,
        @Query("recipe_id") recipe_id: Int,
        @Query("comment") comment: String,
        @Query("date") date: String
    ): APIResponse
    @GET("viewRecipeComments")
    suspend fun viewRecipeComments(@Header("Authorization") token: String): Response<List<Comment>>
    @DELETE("deleteComment")
    suspend fun deleteComment(@Header("Authorization") token: String, @Query("id") id:Int): APIResponse

    // Function yang kurang
    // getFollowingRecipe (Recipes from the user they follow)
    // getCategoryRecipe (Recipes with the category)
    // getUserRecipe

    @GET("viewFollowedRecipe")
    suspend fun viewFollowedRecipe(@Header("Authorization") token: String): Response<List<RecipeResponse>>

    @PATCH("updateUser")
    suspend fun updateUser(
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("name") name: RequestBody,
        @Part("birthdate") birthdate: RequestBody,
        @Part("description") description: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part file: MultipartBody.Part
    ): APIResponse

    @PATCH("updateRecipe")
    suspend fun updateRecipe(
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