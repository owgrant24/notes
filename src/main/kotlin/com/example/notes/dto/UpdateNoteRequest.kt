package com.example.notes.dto

data class UpdateNoteRequest(
    val name: String,
    val description: String?
)
