package com.example.savoria.model

data class Comment(
    val id: Int = 0,
    val user_id: Int = 0,
    val recipe_id: Int = 0,
    val comment: String = "",
    val date: String = "",
)
