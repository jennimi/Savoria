package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.Category
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch
import retrofit2.Response

sealed interface SearchUIState {
    data class Success(val allCategories: Response<List<Category>>) : SearchUIState
    object Error : SearchUIState
    object Loading : SearchUIState
}

class SearchViewModel() : ViewModel() {
    var searchUIState: SearchUIState by mutableStateOf(SearchUIState.Loading)
        private set

    lateinit var allCategories: Response<List<Category>>

    init {
        initializeUiState()
    }

    fun initializeUiState() {
        viewModelScope.launch {
            allCategories = SavoriaContainer().SavoriaRepositories.getCategories(SavoriaContainer.ACCESS_TOKEN)
            searchUIState = SearchUIState.Success(allCategories)
        }
    }
}