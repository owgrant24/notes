package com.example.notes.category.service

import com.example.notes.core.category.entity.Category
import com.example.notes.category.dto.CategoryDto
import com.example.notes.category.dto.CreateCategoryRequest
import com.example.notes.common.exception.AppException
import com.example.notes.common.util.DATE_TIME_FORMATTER
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
        val id: Long = category.id ?: throw AppException("Invalid data")
        val createdAt: String = DATE_TIME_FORMATTER.format(category.createdAt)
        val updatedAt: String = DATE_TIME_FORMATTER.format(category.updatedAt)
        return CategoryDto(id, createdAt, updatedAt, category.name, category.description)
    }
}