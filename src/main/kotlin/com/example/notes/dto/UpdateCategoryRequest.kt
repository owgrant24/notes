package com.example.notes.dto

import javax.validation.constraints.NotBlank

data class UpdateCategoryRequest(
    @field:NotBlank
    val name: String,
    val description: String?
)
