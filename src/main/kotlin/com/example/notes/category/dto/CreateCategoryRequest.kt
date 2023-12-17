package com.example.notes.category.dto

import javax.validation.constraints.NotBlank

data class CreateCategoryRequest(
    @field:NotBlank
    val name: String,
    val description: String?
)
