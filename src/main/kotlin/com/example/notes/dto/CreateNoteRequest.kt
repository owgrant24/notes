package com.example.notes.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateNoteRequest(
    @field:NotBlank
    val name: String,
    val description: String?,
    @field:NotNull
    val categoryId: Long?
)
