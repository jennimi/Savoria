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
import retrofit2.Response

sealed interface HomeUIState {
    data class Success(val data1: Response<User>) : HomeUIState
    object Error : HomeUIState
    object Loading : HomeUIState

}

class UserViewModel() : ViewModel() {
    var homeUIState: HomeUIState by mutableStateOf(HomeUIState.Loading)
        private set

    lateinit var data1: Response<User>

    init {
        getCurrentUser()
    }

//    val listOfUsers: List<User> = SavoriaContainer().SavoriaRepositories.getUsers(SavoriaContainer.ACCESS_TOKEN)

    private fun getAllUsers() {
        viewModelScope.launch {
            try {
            }catch(e: Exception){
                Log.d("FAILED", e.message.toString())
                homeUIState = HomeUIState.Error
            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            data1 = SavoriaContainer().SavoriaRepositories.getUser(SavoriaContainer.ACCESS_TOKEN)
            homeUIState = HomeUIState.Success(data1)
        }
    }
}