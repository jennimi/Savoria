package com.example.savoria.repository

import com.example.savoria.model.User
import com.example.savoria.service.SavoriaService
import java.net.HttpURLConnection

class SavoriaRepositories(private val savoriaService: SavoriaService) {

    suspend fun login(email: String, password: String): String{
        val user = User(email = email, password = password)
        val result = savoriaService.login(user)
        if(result.status.toInt() == HttpURLConnection.HTTP_OK){
            return result.data as String
        }
        return result.message
    }

    suspend fun logout(token: String): String{
        val result = savoriaService.logout(token)
        return result.message
    }

    suspend fun register(user: User): String{
        val result = savoriaService.register(user)
        if(result.status.toInt() == HttpURLConnection.HTTP_OK){
            return result.data as String
        }
        return result.message
    }

    suspend fun viewUserDetails(userId: Int): User {
        val result = savoriaService.viewUserDetails(userId)
        return result.data as User
    }

}