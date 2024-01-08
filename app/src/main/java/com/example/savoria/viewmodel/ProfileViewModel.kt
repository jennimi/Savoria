package com.example.savoria.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.User
import com.example.savoria.model.UserDetails
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.properties.Delegates

sealed interface ProfileUIState {
    data class Success(val userInSessionDetails: Response<UserDetails>, val number: Int, val id: Int) : ProfileUIState
    object Error : ProfileUIState
    object Loading : ProfileUIState

}

class ProfileViewModel() : ViewModel() {
    var profileUIState: ProfileUIState by mutableStateOf(ProfileUIState.Loading)
        private set

    lateinit var userInSessionDetails: Response<UserDetails>
    var number by Delegates.notNull<Int>()
    var iduser by Delegates.notNull<Int>()

    init {
        getCurrentUserDetails()
    }

//    val listOfUsers: List<User> = SavoriaContainer().SavoriaRepositories.getUsers(SavoriaContainer.ACCESS_TOKEN)
//    private fun getAllUsers() {
//        viewModelScope.launch {
//            try {
//            }catch(e: Exception){
//                Log.d("FAILED", e.message.toString())
//                homeUIState = HomeUIState.Error
//            }
//        }
//    }

    fun getUserDetails(id: Int) {
        viewModelScope.launch {
            userInSessionDetails = SavoriaContainer().SavoriaRepositories.viewUserDetails(SavoriaContainer.ACCESS_TOKEN, id)
        }
    }

    fun getCurrentUserDetails() {
        viewModelScope.launch {
            userInSessionDetails = SavoriaContainer().SavoriaRepositories.viewUserDetails(SavoriaContainer.ACCESS_TOKEN, SavoriaContainer.USER_ID)
            number = 100
            iduser = SavoriaContainer.USER_ID
            profileUIState = ProfileUIState.Success(userInSessionDetails, number, iduser)
        }
    }

}