package com.example.savoria.model

data class Comment(
    val id: Int = 0,
    val user_id: Int = 0,
    val recipe_id: Int = 0,
    val comment: String = "",
    val date: String = "",
)

data class CommentAndUser(
    val id: Int = 0,
    val comment: String = "",
    val username: String = "",
    val user_image: String = "",
    val user_id: Int = 0
)
