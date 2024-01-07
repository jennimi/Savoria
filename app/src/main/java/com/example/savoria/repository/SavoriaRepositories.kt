package com.example.savoria.repository

import com.example.savoria.model.APIResponse
import com.example.savoria.model.LoginResponse
import com.example.savoria.model.User
import com.example.savoria.service.SavoriaService
import retrofit2.Response
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
    suspend fun getUsers(token: String): List<User> {
        return savoriaService.viewUser(token)
    }

    suspend fun viewUserDetails(userid: Int): User {
        return savoriaService.viewUserDetails(userid)
    }

    suspend fun getUser(token: String): Response <User> {
        return savoriaService.getUser(token)
    }

}