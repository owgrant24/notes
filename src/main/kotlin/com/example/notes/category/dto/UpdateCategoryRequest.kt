package com.example.notes.category.dto

import jakarta.validation.constraints.NotBlank

data class UpdateCategoryRequest(
    @field:NotBlank
    val name: String,
    val description: String?
)
