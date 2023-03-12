package com.example.notes.service

import com.example.notes.db.repository.CategoryRepository
import com.example.notes.dto.CategoryDto
import com.example.notes.dto.CreateCategoryRequest
import com.example.notes.dto.UpdateCategoryRequest
import com.example.notes.exception.AppException
import com.example.notes.mapper.CategoryMapper
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val categoryMapper: CategoryMapper
) {

    @Transactional
    fun create(dto: CreateCategoryRequest): Long {
        val category = categoryMapper.mapToCategory(dto)
        categoryRepository.save(category)
        return category.id ?: throw AppException("Invalid data")
    }

    @Transactional
    fun getCategories(): List<CategoryDto> {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC,"id"))
            .map { category -> categoryMapper.mapToCategoryDto(category) }
    }

    @Transactional
    fun getCategory(id: Long): CategoryDto {
        val category = categoryRepository.getReferenceById(id)
        return categoryMapper.mapToCategoryDto(category)
    }

    @Transactional
    fun update(id: Long, dto: UpdateCategoryRequest) {
        val category = categoryRepository.getReferenceById(id)
        category.name = dto.name
        category.description = dto.description
        categoryRepository.save(category)
    }

    @Transactional
    fun delete(id: Long) {
        categoryRepository.deleteById(id)
    }
}