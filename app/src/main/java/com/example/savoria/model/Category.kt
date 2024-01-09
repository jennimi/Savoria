package com.example.savoria.model

import java.util.Date

data class Category(
    val id: Int = 0,
    val name: String = "",
    val category_image: String = ""
)

data class CategoryResponse(
    val id: Int = 0,
    val name: String = "",
    val created_at: Date,
    val updated_at: Date,
    val pivot: RecipeCategory? = null
)