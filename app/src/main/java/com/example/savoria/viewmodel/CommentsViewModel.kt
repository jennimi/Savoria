package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savoria.model.Comment
import com.example.savoria.model.CommentAndUser
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.UserResponse
import com.example.savoria.repository.SavoriaContainer
import kotlinx.coroutines.launch
import retrofit2.Response

sealed interface CommentsUIState {
    data class Success(val comments: Response<List<CommentAndUser>>) : CommentsUIState
    object Error : CommentsUIState
    object Loading : CommentsUIState
}

class CommentsViewModel() : ViewModel() {
    var commentsUIState: CommentsUIState by mutableStateOf(CommentsUIState.Loading)
        private set

    lateinit var comments: Response<List<CommentAndUser>>

    fun initializeComments(id: Int) {
        viewModelScope.launch {
            try {
                comments = SavoriaContainer().SavoriaRepositories.getUserComments(SavoriaContainer.ACCESS_TOKEN, id)
                commentsUIState = CommentsUIState.Success(comments)
            } catch (e: Exception) {
                CommentsUIState.Error
            }
        }
    }
}