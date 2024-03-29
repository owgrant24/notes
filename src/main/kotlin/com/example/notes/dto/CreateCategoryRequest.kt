package com.example.notes.dto

import javax.validation.constraints.NotBlank

data class CreateCategoryRequest(
    @field:NotBlank
    val name: String,
    val description: String?
)
