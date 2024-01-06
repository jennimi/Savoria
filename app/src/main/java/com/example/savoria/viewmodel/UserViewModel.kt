package com.example.savoria.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.savoria.data.DataStoreManager
import com.example.savoria.repository.SavoriaContainer
import com.example.savoria.ui.Screen
import com.example.savoria.model.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    fun ButtonLogin(
        email: String,
        password: String,
        context: Context,
        navController: NavController,
        dataStore: DataStoreManager
    ){
        viewModelScope.launch {
            val token = SavoriaContainer().SavoriaRepositories.login(email, password)
            when {
                token.equals("Incorrect Password") || token.equals("User not found") -> {
                    Toast.makeText(context, token.toString(), Toast.LENGTH_LONG).show()
                }
                else -> {
                    navController.navigate(Screen.Home.name) {
                        popUpTo(Screen.Login.name) { inclusive = true }
                    }
                    dataStore.saveToken(token.toString())

                    dataStore.getToken.collect { storedToken ->
                        storedToken?.let {
                            SavoriaContainer.ACCESS_TOKEN = it
                        }
                    }
                }
            }
        }
    }

    fun ButtonRegister(
        user: User,
        navController: NavController,
        dataStore: DataStoreManager
    ){
        viewModelScope.launch {
            val token = SavoriaContainer().SavoriaRepositories.register(user)

        }
    }

    fun ButtonLogOut(
        navController: NavController,
        dataStore: DataStoreManager
    ) {
        viewModelScope.launch {
//            val token = SavoriaContainer().SavoriaRepositories.logout()

//            SavoriaContainer().SavoriaRepositories.logout()

            try {
                SavoriaContainer().SavoriaRepositories.logout()
                // Other code
            } catch (e: Exception) {
                // Handle the exception (e.g., display a message or log the error)
                e.printStackTrace()
            }


            dataStore.saveToken("")

            SavoriaContainer.ACCESS_TOKEN = ""

            navController.navigate(Screen.Login.name)
        }
    }
}