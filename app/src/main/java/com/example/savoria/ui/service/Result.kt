package com.example.savoria.ui.service

data class Result<out T>(val status: String, val data: T?, val message: String)