package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.User
import com.example.savoria.model.UserResponse
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch
import retrofit2.Response

sealed interface ProfileUIState {
    data class Success(val userInSessionDetails: Response<UserResponse>, val userInSession: Response<User>, val recipesByUser: Response<List<RecipeResponse>>, val recipesSaved: Response<List<RecipeResponse>>) : ProfileUIState
    object Error : ProfileUIState
    object Loading : ProfileUIState

}

class ProfileViewModel() : ViewModel() {
    var profileUIState: ProfileUIState by mutableStateOf(ProfileUIState.Loading)
        private set

    lateinit var userInSessionDetails: Response<UserResponse>
    lateinit var userInSession: Response<User>
    lateinit var recipesByUser: Response<List<RecipeResponse>>
    lateinit var recipesSaved: Response<List<RecipeResponse>>

    init {
        getCurrentUserDetails()
    }

    fun getUserDetails(id: Int) {
        viewModelScope.launch {
            userInSessionDetails = SavoriaContainer().SavoriaRepositories.getUserDetails(SavoriaContainer.ACCESS_TOKEN, id)
        }
    }

    fun getCurrentUserDetails() {
        viewModelScope.launch {
            userInSession = SavoriaContainer().SavoriaRepositories.getUser(SavoriaContainer.ACCESS_TOKEN)
            val userid: Int = userInSession.body()!!.id
            userInSessionDetails = SavoriaContainer().SavoriaRepositories.getUserDetails(SavoriaContainer.ACCESS_TOKEN, userid)
            recipesByUser = SavoriaContainer().SavoriaRepositories.getRecipeByUser(SavoriaContainer.ACCESS_TOKEN, userid)
            recipesSaved = SavoriaContainer().SavoriaRepositories.getFavoriteRecipes(SavoriaContainer.ACCESS_TOKEN, userid)

            profileUIState = ProfileUIState.Success(userInSessionDetails, userInSession, recipesByUser, recipesSaved)
        }
    }


}