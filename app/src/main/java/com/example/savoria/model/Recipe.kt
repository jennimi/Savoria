package com.example.savoria.model

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