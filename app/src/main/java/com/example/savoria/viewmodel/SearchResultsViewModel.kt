package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.Category
import com.example.savoria.model.RecipeResponse
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch
import retrofit2.Response

sealed interface SearchResultsUiState{
    data class Success(val searchResults: Response<List<RecipeResponse>>): SearchResultsUiState
    object Error: SearchResultsUiState
    object Loading: SearchResultsUiState
}

class SearchResultsViewModel() : ViewModel() {

    lateinit var searchResults: Response<List<RecipeResponse>>
    var searchResultsUiState: SearchResultsUiState by mutableStateOf(SearchResultsUiState.Loading)
        private set
    fun getSearchResults(search: String) {
        viewModelScope.launch {
            try {
                searchResults = SavoriaContainer().SavoriaRepositories.searchRecipes(SavoriaContainer.ACCESS_TOKEN, search)
                searchResultsUiState = SearchResultsUiState.Success(searchResults)
            } catch (e: Exception) {
                SearchResultsUiState.Error
            }
        }
    }
}