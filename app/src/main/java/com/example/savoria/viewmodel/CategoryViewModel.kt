package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.Category
import com.example.savoria.model.Comment
import com.example.savoria.model.CommentAndUser
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.UserResponse
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch
import retrofit2.Response

sealed interface CategoryUIState {
    data class Success(val allRecipes: Response<List<RecipeResponse>>, val categoryName: String) : CategoryUIState
    object Error : CategoryUIState
    object Loading : CategoryUIState
}

class CategoryViewModel() : ViewModel() {
    var categoryUIState: CategoryUIState by mutableStateOf(CategoryUIState.Loading)
        private set

    lateinit var allRecipes: Response<List<RecipeResponse>>
    lateinit var categoryName: String

    fun initializeCategory(id: Int) {
        viewModelScope.launch {
            try {
                allRecipes = SavoriaContainer().SavoriaRepositories.getRecipeByCategory(SavoriaContainer.ACCESS_TOKEN, id)

                val allCategoryResponse: Response<List<Category>> = SavoriaContainer().SavoriaRepositories.getCategories(SavoriaContainer.ACCESS_TOKEN)
                val allCategory = allCategoryResponse.body()
                categoryName = allCategory?.get(id)!!.name
                categoryUIState = CategoryUIState.Success(allRecipes, categoryName)
            } catch (e: Exception) {
                CategoryUIState.Error
            }
        }
    }
}