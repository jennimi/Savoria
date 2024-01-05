package com.example.savoria.ui.repository

import com.example.savoria.ui.model.User
import com.example.savoria.ui.service.MyDBService
import java.net.HttpURLConnection

import com.example.savoria.ui.data.LoginResult

class MyDBRepositories(private val myDBService: MyDBService) {

    suspend fun login(email: String, password: String): LoginResult {
        val user = User(email = email, password = password)
        val result = myDBService.login(user)
        if(result.status.toInt() == HttpURLConnection.HTTP_OK){
            return LoginResult(status = result.status, data = result.data as String, message = "")
        }
        return LoginResult(status = result.status, data = null, message = result.message)

//        return when {
//            result.status == HttpURLConnection.HTTP_OK -> {
//                LoginResult(status = result.status, data = result.data as String, message = "")
//            }
//            else -> {
//                LoginResult(status = result.status, data = null, message = result.message)
//            }
//        }
    }

    suspend fun logout(): String{
        val result = myDBService.logout()
        return result.message
    }

    suspend fun register(user: User): String{
        val result = myDBService.register(user)
        if(result.status.toInt() == HttpURLConnection.HTTP_OK){
            return result.data as String
        }
        return result.message
    }

}