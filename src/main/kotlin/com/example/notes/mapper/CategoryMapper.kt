package com.example.notes.mapper

import com.example.notes.db.entity.Category
import com.example.notes.dto.CategoryDto
import com.example.notes.dto.CreateCategoryRequest
import com.example.notes.exception.AppException
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
        return CategoryDto(id, category.createdAt, category.updatedAt, category.name, category.description)
    }
}