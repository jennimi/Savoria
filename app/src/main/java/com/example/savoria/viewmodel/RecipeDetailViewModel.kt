package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.RecipeResponse
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch
import retrofit2.Response


sealed interface RecipeDetailUIState {
    data class Success(val recipe: Response<RecipeResponse>) : RecipeDetailUIState
    object Error : RecipeDetailUIState
    object Loading : RecipeDetailUIState
}

class RecipeDetailViewModel() : ViewModel() {
    var recipeDetailUIState: RecipeDetailUIState by mutableStateOf(RecipeDetailUIState.Loading)
        private set

    lateinit var recipe: Response<RecipeResponse>


    fun initializeRecipeId(id: Int) {
        viewModelScope.launch {
            try {
                recipe = SavoriaContainer().SavoriaRepositories.getRecipeDetails(SavoriaContainer.ACCESS_TOKEN, id)
                recipeDetailUIState = RecipeDetailUIState.Success(recipe)
            } catch (e: Exception) {
                RecipeDetailUIState.Error
            }

        }

    }
}