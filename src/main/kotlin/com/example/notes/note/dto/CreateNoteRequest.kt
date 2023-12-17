package com.example.notes.note.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateNoteRequest(
    @field:NotBlank
    val name: String,
    val description: String?,
    @field:NotNull
    val categoryId: Long?
)
