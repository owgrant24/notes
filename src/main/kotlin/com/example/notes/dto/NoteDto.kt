package com.example.notes.dto

import java.time.LocalDateTime

data class NoteDto(
    val id: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val name: String,
    val description: String?
)
