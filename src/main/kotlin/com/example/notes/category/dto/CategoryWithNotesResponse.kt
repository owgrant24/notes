package com.example.notes.category.dto

import com.example.notes.common.dto.PageResponse

data class CategoryWithNotesResponse(
    val category: CategoryDto,
    val page: PageResponse<NoteDto>,
)
