package com.example.notes.core.category.service

import com.example.notes.common.exception.AppException
import com.example.notes.core.category.entity.Category
import com.example.notes.core.category.repo.CategoryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun getById(id: Long): Category = categoryRepository.findById(id)
        .orElseThrow { AppException("Category with id: $id not found") };

    fun findAll(pageRequest: PageRequest): Page<Category> {
        return categoryRepository.findAll(pageRequest)
    }

    fun save(category: Category) {
        categoryRepository.save(category)
    }

    fun delete(category: Category) {
        categoryRepository.delete(category)
    }
}