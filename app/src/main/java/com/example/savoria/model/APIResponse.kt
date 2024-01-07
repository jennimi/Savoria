package com.example.savoria.model

data class APIResponse (
    val status: String = "",
    val message: String = "",
    val data: Any
)

data class LoginResponse (
    val status: String = "",
    val message: String = "",
    val userid: Int = 0,
    val token: String = "",
)