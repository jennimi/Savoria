package com.example.savoria.viewmodel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.savoria.data.DataStoreManager
import com.example.savoria.model.LoginResponse
import com.example.savoria.repository.SavoriaContainer
import com.example.savoria.ui.Screen
import com.example.savoria.ui.view.boarding.isValidEmail
import com.example.savoria.ui.view.boarding.isValidPassword
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    fun Login(
        email: String,
        password: String,
        context: Context,
        navController: NavController,
        dataStore: DataStoreManager
    ){
        viewModelScope.launch {
            val token: LoginResponse = SavoriaContainer().SavoriaRepositories.login(email, password)
            when {
                token.message.equals("Incorrect Password") || token.message.equals("User not found") -> {
                    Toast.makeText(context, token.toString(), Toast.LENGTH_LONG).show()
                }
                else -> {
                    SavoriaContainer.ACCESS_TOKEN = token.token
                    SavoriaContainer.USER_ID = token.userid
                    dataStore.saveToken(token.token, token.userid)
                    dataStore.getToken.collect {token1->
                        SavoriaContainer.ACCESS_TOKEN = token1.toString()

                        navController.navigate(Screen.Home.name) {
                            popUpTo(Screen.Login.name) { inclusive = true }
                        }
                    }
                }
            }
        }
    }

    fun Register (
        username: String,
        email: String,
        password: String,
        name: String,
        birthdate: String,
        description: String,
        phone: String,
        gender: String,
        selectedImageUri: Uri,
        context: Context,
        navController: NavController,
        dataStore: DataStoreManager
    ){
        viewModelScope.launch {
            val validationError = validateRegistrationInput(
                username,
                email,
                password,
                name,
                birthdate,
                description,
                phone,
                gender,
            )

            if (validationError == null) {
                try {
                    SavoriaContainer().SavoriaRepositories.register(
                        username,
                        email,
                        password,
                        name,
                        birthdate,
                        description,
                        phone,
                        gender,
                        selectedImageUri,
                        context
                    )

                    val token: LoginResponse = SavoriaContainer().SavoriaRepositories.login(email, password)
                    when {
                        token.message.equals("Incorrect Password") || token.message.equals("User not found") -> {
                            Toast.makeText(context, token.toString(), Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            SavoriaContainer.ACCESS_TOKEN = token.token
                            SavoriaContainer.USER_ID = token.userid
                            dataStore.saveToken(token.token, token.userid)
                            dataStore.getUserid.collect {userid1->
                                if (userid1 != null) {
                                    SavoriaContainer.USER_ID = userid1.toInt()
                                }
                            }
                            dataStore.getToken.collect {token1->
                                SavoriaContainer.ACCESS_TOKEN = token1.toString()

                                navController.navigate(Screen.Home.name) {
                                    popUpTo(Screen.Login.name) { inclusive = true }
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                navController.navigate(Screen.Register.name)
            }
        }
    }

    private fun validateRegistrationInput(
        username: String,
        email: String,
        password: String,
        name: String,
        birthdate: String,
        description: String,
        phone: String,
        gender: String
    ): String? {
        if (!isValidEmail(email)) {
            return "Invalid email format"
        }

        if (!isValidPassword(password)) {
            return "Invalid password format"
        }
        return null
    }

    fun LogOut(
        navController: NavController,
        dataStore: DataStoreManager
    ) {
        viewModelScope.launch {
            SavoriaContainer().SavoriaRepositories.logout(SavoriaContainer.ACCESS_TOKEN)
            SavoriaContainer.ACCESS_TOKEN = ""

            navController.navigate(Screen.AppIntro.name)
        }
    }

}