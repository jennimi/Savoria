package com.example.savoria.model

data class User(
    val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val birthdate: String = "",
    val description: String = "",
    val phone: String = "",
    val gender: String = "",
    val profile_picture: String = "",
)