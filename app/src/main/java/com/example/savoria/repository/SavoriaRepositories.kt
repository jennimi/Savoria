package com.example.savoria.repository

import com.example.savoria.model.APIResponse
import com.example.savoria.model.LoginResponse
import com.example.savoria.model.User
import com.example.savoria.service.SavoriaService
import java.net.HttpURLConnection

class SavoriaRepositories(private val savoriaService: SavoriaService) {

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

    suspend fun getUsers(token: String): List<User> {
        val result = savoriaService.viewUser(token)
        return result as List<User>
    }

    suspend fun viewUserDetails(userid: Int): User {
        val result = savoriaService.viewUserDetails(userid)
        return result as User
    }

}