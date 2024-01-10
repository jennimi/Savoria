package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.UserResponse
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch
import retrofit2.Response


sealed interface RecipeDetailUIState {
    data class Success(val recipe: Response<RecipeResponse>, val user: Response<UserResponse>) : RecipeDetailUIState
    object Error : RecipeDetailUIState
    object Loading : RecipeDetailUIState
}

class RecipeDetailViewModel() : ViewModel() {
    var recipeDetailUIState: RecipeDetailUIState by mutableStateOf(RecipeDetailUIState.Loading)
        private set

    lateinit var recipe: Response<RecipeResponse>
    lateinit var user: Response<UserResponse>


    fun initializeRecipeId(id: Int) {
        viewModelScope.launch {
            try {
                recipe = SavoriaContainer().SavoriaRepositories.getRecipeDetails(SavoriaContainer.ACCESS_TOKEN, id)

                val recipeVal: RecipeResponse = recipe.body()!!
                val userid: Int = recipeVal.user_id.toInt()

                user = SavoriaContainer().SavoriaRepositories.getUserDetails(SavoriaContainer.ACCESS_TOKEN, userid)

                recipeDetailUIState = RecipeDetailUIState.Success(recipe, user)
            } catch (e: Exception) {
                RecipeDetailUIState.Error
            }
        }
    }

    fun favoriteRecipe(recipeid: Int) {
        viewModelScope.launch {
            SavoriaContainer().SavoriaRepositories.addFavorite(SavoriaContainer.ACCESS_TOKEN, recipeid)
        }
    }

    fun unfavoriteRecipe(recipeid: Int) {
        viewModelScope.launch {
            SavoriaContainer().SavoriaRepositories.removeFavorite(SavoriaContainer.ACCESS_TOKEN, recipeid)
        }
    }
}