package com.example.savoria.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.savoria.data.DataStoreManager
import com.example.savoria.repository.MyDBContainer
import com.example.savoria.ui.Screen
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    fun ButtonLogin(
        email: String,
        password: String,
        context: Context,
        navController: NavController,
        dataStore: DataStoreManager
    ){
        viewModelScope.launch {
            val token = MyDBContainer().myDBRepositories.login(email, password)
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
                            MyDBContainer.ACCESS_TOKEN = it
                        }
                    }
                }
            }
        }
    }

    fun ButtonRegister(){

    }
}