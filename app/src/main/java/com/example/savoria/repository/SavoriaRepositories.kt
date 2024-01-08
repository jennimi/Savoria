package com.example.savoria.repository

import android.content.Context
import android.net.Uri
import com.example.savoria.model.LoginResponse
import com.example.savoria.model.User
import com.example.savoria.model.UserDetails
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
        if(result.status.toInt() == HttpURLConnection.HTTP_OK){
            return result
        }
        return result
    }

    suspend fun logout(token: String){
        return savoriaService.logout(token)
    }

    suspend fun register(user: User): String{
        val result = savoriaService.register(user)
        if(result.status.toInt() == HttpURLConnection.HTTP_OK){
            return result.data as String
        }
        return result.message
    }
    //auth

    //user
    suspend fun getUser(token: String): Response <User> {
        return savoriaService.getUser(token)
    }

    suspend fun getUsers(token: String): List<User> {
        return savoriaService.viewUser(token)
    }
    suspend fun viewUserDetails(token: String, id: Int): Response<UserDetails> {
        return savoriaService.viewUserDetails(token, id)
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
        val userResponse = SavoriaContainer().SavoriaRepositories.getUser(SavoriaContainer.ACCESS_TOKEN)

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
            val file = File(fileDir,"image.png")

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
            if(result.status.toInt() == HttpURLConnection.HTTP_OK){
                return result.data as String
            }
            return result.message
        } else {
            // Handle the case when the user retrieval is not successful
            return "User retrieval failed: ${userResponse.code()}"
        }
    }
    //recipe
}