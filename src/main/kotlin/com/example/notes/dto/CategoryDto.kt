package com.example.notes.dto

data class CategoryDto(
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val name: String,
    val description: String?
)
