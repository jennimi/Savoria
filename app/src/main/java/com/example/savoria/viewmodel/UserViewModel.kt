package com.example.savoria.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.savoria.data.DataStoreManager
import com.example.savoria.repository.SavoriaContainer
import com.example.savoria.ui.Screen
import com.example.savoria.model.User
import com.example.savoria.ui.view.boarding.isValidEmail
import com.example.savoria.ui.view.boarding.isValidPassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
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

    fun ButtonRegister (
        username: String,
        email: String,
        password: String,
        name: String,
        birthdate: String,
        description: String,
        phone: String,
        gender: String,
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
                gender
            )

            if (validationError == null) {
                val user = User(
                    username = username,
                    email = email,
                    password = password,
                    name = name,
                    birthdate = birthdate,
                    description = description,
                    phone = phone,
                    gender = gender
                )

                try {
                    val token = SavoriaContainer().SavoriaRepositories.register(user)
                    navController.navigate(Screen.Home.name) {
                        popUpTo(Screen.Login.name) { inclusive = true }
                    }
                    dataStore.saveToken(token.toString())

                    dataStore.getToken.collect { storedToken ->
                        storedToken?.let {
                            SavoriaContainer.ACCESS_TOKEN = it
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                // Haven't added error message
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

    fun ButtonLogOut(
        navController: NavController,
        dataStore: DataStoreManager
    ) {
        viewModelScope.launch {
            try {
                SavoriaContainer().SavoriaRepositories.logout(SavoriaContainer.ACCESS_TOKEN)
                dataStore.saveToken("")
                SavoriaContainer.ACCESS_TOKEN = ""
                navController.navigate(Screen.Login.name)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    }

    private val _uiState = MutableStateFlow(User())
    val uiState: StateFlow<User> = _uiState.asStateFlow()
    fun ViewUserDetails(
        navController: NavController,
        dataStore: DataStoreManager
    ) {
        viewModelScope.launch {
            val token = dataStore.getToken.firstOrNull()
            if (token.isNullOrBlank()) {
                navController.navigate(Screen.AppIntro.name)
            } else {
                try {
                    val user = SavoriaContainer().SavoriaRepositories.viewUserDetails(1)
                    _uiState.value = user
                } catch (e: Exception) {
                    e.printStackTrace()
                    navController.navigate(Screen.Register.name)
                }
            }
        }
    }
}