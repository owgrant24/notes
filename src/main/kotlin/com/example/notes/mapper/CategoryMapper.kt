package com.example.notes.mapper

import com.example.notes.db.entity.Category
import com.example.notes.dto.CategoryDto
import com.example.notes.dto.CreateCategoryRequest
import com.example.notes.exception.AppException
import com.example.notes.util.DATE_TIME_FORMATTER
import org.springframework.stereotype.Component

@Component
class CategoryMapper {

    fun mapToCategory(createCategoryRequest: CreateCategoryRequest): Category {
        val category = Category()
        category.name = createCategoryRequest.name
        category.description = createCategoryRequest.description
        return category
    }

    fun mapToCategoryDto(category: Category): CategoryDto {
        val id = category.id ?: throw AppException("Invalid data")
        val createdAt = DATE_TIME_FORMATTER.format(category.createdAt)
        val updatedAt = DATE_TIME_FORMATTER.format(category.updatedAt)
        return CategoryDto(id, createdAt, updatedAt, category.name, category.description)
    }
}