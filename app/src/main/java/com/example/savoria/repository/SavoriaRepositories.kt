package com.example.savoria.repository

import android.content.Context
import android.net.Uri
import com.example.savoria.model.APIResponse
import com.example.savoria.model.Category
import com.example.savoria.model.Comment
import com.example.savoria.model.LoginResponse
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.User
import com.example.savoria.model.UserResponse
import com.example.savoria.service.SavoriaService
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection

class SavoriaRepositories(private val savoriaService: SavoriaService) {

    //start auth
    suspend fun login(email: String, password: String): LoginResponse {
        val user = User(email = email, password = password)
        val result = savoriaService.login(user)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result
        }
        return result
    }

    suspend fun logout(token: String) {
        return savoriaService.logout(token)
    }

    suspend fun register(
        username: String,
        email: String,
        password: String,
        name: String,
        birthdate: String,
        description: String,
        phone: String,
        gender: String,
        profile_picture: Uri,
        context: Context
    ): String {

        val usernamePart = username.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailPart = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val passwordPart = password.toRequestBody("text/plain".toMediaTypeOrNull())
        val namePart = name.toRequestBody("text/plain".toMediaTypeOrNull())
        val birthdatePart = birthdate.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionPart = description.toRequestBody("text/plain".toMediaTypeOrNull())
        val phonePart = phone.toRequestBody("text/plain".toMediaTypeOrNull())
        val genderPart = gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val fileDir = context.filesDir
        val file = File(fileDir, "image.png")
        val inputStream = context.contentResolver.openInputStream(profile_picture)
        inputStream?.use { input ->
            val outputStream = FileOutputStream(file)
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("file", file.name, requestBody)
        val result = savoriaService.register(
            username = usernamePart,
            email = emailPart,
            password = passwordPart,
            name = namePart,
            birthdate = birthdatePart,
            description = descriptionPart,
            phone = phonePart,
            gender = genderPart,
            file = part
            )

        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result.data as String
        }
        return result.message
    }
    //auth

    //user
    suspend fun getUser(token: String): Response<User> {
        return savoriaService.getUser(token)
    }

    suspend fun getUserDetails(token: String, id: Int): Response<UserResponse> {
        return savoriaService.viewUserDetails(token, id)
    }

    suspend fun getUsers(token: String): Response<List<UserResponse>> {
        return savoriaService.viewUser(token)
    }
    suspend fun deleteUser(token: String, id: Int): APIResponse {
        val result = savoriaService.deleteUser(token, id)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result
        }
        return result
    }
    suspend fun followUser(token: String, id: Int): APIResponse {
        val result = savoriaService.followUser(token, id)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result
        }
        return result
    }
    suspend fun unfollowUser(token: String, id: Int): APIResponse {
        val result = savoriaService.unfollowUser(token, id)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result
        }
        return result
    }
    //user

    //recipe
    suspend fun createRecipe(
        token: String,
        recipe_name: String,
        caption: String,
        ingredients: String,
        steps: String,
        image: Uri,
        calorie: Int,
        servings: Int,
        time: Int,
        categories: List<Int>,
        context: Context
    ): String {
        val userResponse =
            SavoriaContainer().SavoriaRepositories.getUser(SavoriaContainer.ACCESS_TOKEN)

        if (userResponse.isSuccessful) {
            val user = userResponse.body()

            val userJson = Gson().toJson(user)
            val userRequestBody = userJson.toRequestBody("application/json".toMediaTypeOrNull())

            val recipe_namePart = recipe_name.toRequestBody("text/plain".toMediaTypeOrNull())
            val captionPart = caption.toRequestBody("text/plain".toMediaTypeOrNull())
            val ingredientsPart = ingredients.toRequestBody("text/plain".toMediaTypeOrNull())
            val stepsPart = steps.toRequestBody("text/plain".toMediaTypeOrNull())

            val caloriePart = calorie.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val servingsPart = servings.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val timePart = time.toString().toRequestBody("text/plain".toMediaTypeOrNull())

            val categoriesPart = categories.joinToString(",") { it.toString() }
                .toRequestBody("text/plain".toMediaTypeOrNull())

            val fileDir = context.filesDir
            val file = File(fileDir, "image.png")

            val inputStream = context.contentResolver.openInputStream(image)
            inputStream?.use { input ->
                val outputStream = FileOutputStream(file)
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

            val part = MultipartBody.Part.createFormData("file", file.name, requestBody)

            val result = savoriaService.createRecipe(
                token = token,
                recipe_name = recipe_namePart,
                caption = captionPart,
                ingredients = ingredientsPart,
                steps = stepsPart,
                file = part,
                calorie = caloriePart,
                servings = servingsPart,
                time = timePart,
                categories = categoriesPart
            )
            if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
                return result.data as String
            }
            return result.message
        } else {
            // Handle the case when the user retrieval is not successful
            return "User retrieval failed: ${userResponse.code()}"
        }
    }

    suspend fun getRecipes(token: String): Response<List<RecipeResponse>> {
        return savoriaService.viewRecipe(token)
    }

    suspend fun getRecipeDetails(token: String, id: Int): Response<RecipeResponse> {
        return savoriaService.viewRecipeDetails(token, id)
    }
    suspend fun deleteRecipe(token: String, id: Int): APIResponse {
        val result = savoriaService.deleteRecipe(token, id)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result
        }
        return result
    }
    suspend fun addFavorite(token: String, id: Int): APIResponse {
        val result = savoriaService.addFavorite(token, id)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result
        }
        return result
    }
    suspend fun getFavoriteRecipes(token: String, id: Int): APIResponse {
        return savoriaService.viewFavorite(token, id)
    }
    // this one should be Response<List<RecipeResponses>>
    suspend fun removeFavorite(token: String, id: Int): APIResponse {
        val result = savoriaService.deleteFavorite(token, id)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result
        }
        return result
    }

    suspend fun getFollowRecipe(token: String): Response<List<RecipeResponse>> {
        return savoriaService.viewFollowedRecipe(token)
    }

    suspend fun searchRecipes(token: String, search: String): Response<List<RecipeResponse>> {
        return savoriaService.searchRecipes(token, search)
    }

    suspend fun getRecipeByCategory(token: String, id: Int): Response<List<RecipeResponse>> {
        return savoriaService.viewRecipeByCategory(token, id)
    }

    suspend fun getRecipeByUser(token: String, id: Int): Response<List<RecipeResponse>> {
        return savoriaService.viewRecipeByUser(token, id)
    }


    suspend fun getTopSavedRecipe(token: String): Response<List<RecipeResponse>> {
        return savoriaService.viewTopSavedRecipe(token)
    }
    //recipe

    //category
    suspend fun getCategories(token: String): Response<List<Category>> {
        return savoriaService.viewCategory(token)
    }
    //category

    //comment
    suspend fun createComment(token: String, recipe_id: Int, comment: String, date: String): APIResponse {
        val result = savoriaService.createComment(
            token,
            recipe_id,
            comment,
            date
        )
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result
        }
        return result
    }
    suspend fun getUserComments(token: String): Response<List<Comment>> {
        return savoriaService.viewRecipeComments(token)
    }
    suspend fun deleteComment(token: String, id: Int): APIResponse {
        val result = savoriaService.deleteComment(token, id)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result
        }
        return result
    }
    //comment
}