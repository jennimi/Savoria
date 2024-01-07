package com.example.savoria.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.User
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch

sealed interface HomeUIState {
    data class Success(val data: List<User>) : HomeUIState
    object Error : HomeUIState
    object Loading : HomeUIState

}

class UserViewModel() : ViewModel() {
    var homeUIState: HomeUIState by mutableStateOf(HomeUIState.Loading)
        private set

    lateinit var data: List<User>

    init {
        getAllUsers()
    }

//    val listOfUsers: List<User> = SavoriaContainer().SavoriaRepositories.getUsers(SavoriaContainer.ACCESS_TOKEN)

    private fun getAllUsers() {
        viewModelScope.launch {
            try {
                data = SavoriaContainer().SavoriaRepositories.getUsers(SavoriaContainer.ACCESS_TOKEN)
                homeUIState = HomeUIState.Success(data)
            }catch(e: Exception){
                Log.d("FAILED", e.message.toString())
                homeUIState = HomeUIState.Error
            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            val user1 = SavoriaContainer().SavoriaRepositories.viewUserDetails(SavoriaContainer.USER_ID)

        }
    }
}