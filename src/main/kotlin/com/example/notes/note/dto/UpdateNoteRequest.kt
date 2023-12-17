package com.example.notes.note.dto

import jakarta.validation.constraints.NotBlank

data class UpdateNoteRequest(
    @field:NotBlank
    val name: String,
    val description: String?
)
