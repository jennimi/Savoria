package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.User
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch
import retrofit2.Response

sealed interface HomeUIState {
    data class Success(val userInSession: Response<User>, val allRecipe: Response<List<RecipeResponse>>) : HomeUIState
    object Error : HomeUIState
    object Loading : HomeUIState

}

class HomeViewModel() : ViewModel() {
    var homeUIState: HomeUIState by mutableStateOf(HomeUIState.Loading)
        private set

    lateinit var userInSession: Response<User>
    lateinit var allRecipe: Response<List<RecipeResponse>>

    init {
        initializeUiState()
    }

//    val listOfUsers: List<User> = SavoriaContainer().SavoriaRepositories.getUsers(SavoriaContainer.ACCESS_TOKEN)

    fun getCurrentUser() {
        viewModelScope.launch {
            userInSession = SavoriaContainer().SavoriaRepositories.getUser(SavoriaContainer.ACCESS_TOKEN)
        }
    }

    fun getAllRecipes() {
        viewModelScope.launch {
            allRecipe = SavoriaContainer().SavoriaRepositories.getRecipes(SavoriaContainer.ACCESS_TOKEN)
        }
    }

    fun initializeUiState() {
        viewModelScope.launch {
            userInSession = SavoriaContainer().SavoriaRepositories.getUser(SavoriaContainer.ACCESS_TOKEN)
            allRecipe = SavoriaContainer().SavoriaRepositories.getRecipes(SavoriaContainer.ACCESS_TOKEN)
            homeUIState = HomeUIState.Success(userInSession, allRecipe)
        }
    }
}