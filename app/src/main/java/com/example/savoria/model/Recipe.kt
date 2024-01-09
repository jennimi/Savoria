package com.example.savoria.model

import retrofit2.Response

data class Recipe(
    val recipe_name: String = "",
    val caption: String = "",
    val ingredients: String = "",
    val steps: String = "",
    val image: String = "",
    val calorie: String = "",
    val servings: String = "",
    val time: String = "",
    val user_id: String = "",
    val id: Int = 0,
)

data class RecipeResponse (
    val id: Int = 0,
    val user_id: String = "",
    val recipe_name: String = "",
    val caption: String = "",
    val ingredients: String = "",
    val steps: String = "",
    val image: String = "",
    val calorie: Int = 0,
    val servings: Int = 0,
    val time: Int = 0,
    val categories: List<CategoryResponse>? = null,
    val comments: List<Comment>? = null,
    val saved_by_users_count: Int = 0,
)