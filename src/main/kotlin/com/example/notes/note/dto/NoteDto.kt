package com.example.notes.note.dto

data class NoteDto(
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val name: String,
    val description: String?
)
