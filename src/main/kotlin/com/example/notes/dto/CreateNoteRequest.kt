package com.example.notes.dto

data class CreateNoteRequest(
    val name: String,
    val description: String?
)
