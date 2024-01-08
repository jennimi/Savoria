package com.example.savoria.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.savoria.repository.SavoriaContainer
import com.example.savoria.ui.Screen
import kotlinx.coroutines.launch

class RecipeViewModel() : ViewModel() {
    fun createRecipe(
        recipeName: String,
        caption: String,
        ingredients: String,
        steps: String,
        selectedImageUri: Uri,
        calories: Int,
        serving: Int,
        time: Int,
        context: Context,
        navController: NavController
    ) {
        val categories: List<Int> = listOf(1, 2, 3)
        viewModelScope.launch {
            SavoriaContainer().SavoriaRepositories.createRecipe(
                SavoriaContainer.ACCESS_TOKEN,
                recipeName,
                caption,
                ingredients,
                steps,
                selectedImageUri,
                calories,
                serving,
                time,
                categories,
                context
            )
            navController.navigate(Screen.Home.name)
        }
    }
}