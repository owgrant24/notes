package com.example.notes.category.service

import com.example.notes.core.category.repo.CategoryRepository
import com.example.notes.category.dto.CategoryDto
import com.example.notes.category.dto.CreateCategoryRequest
import com.example.notes.common.dto.PageResponse
import com.example.notes.category.dto.UpdateCategoryRequest
import com.example.notes.common.exception.AppException
import com.example.notes.core.category.entity.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private const val PAGE_SIZE = 12

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val categoryMapper: CategoryMapper
) {

    @Transactional
    fun create(dto: CreateCategoryRequest): Long {
        val category: Category = categoryMapper.mapToCategory(dto)
        categoryRepository.save(category)
        return category.id ?: throw AppException("Invalid data")
    }

    @Transactional(readOnly = true)
    fun getCategories(pageIndex: Int?): PageResponse<CategoryDto> {
        var pageNumber: Int = pageIndex ?: 1
        if (pageNumber < 1) {
            pageNumber = 1
        }
        val pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.by(Sort.Direction.ASC, "id"))
        val page: Page<CategoryDto> = categoryRepository.findAll(pageRequest)
                .map(categoryMapper::mapToCategoryDto)
        return PageResponse.from(page)
    }

    @Transactional(readOnly = true)
    fun getCategory(id: Long): CategoryDto {
        val category: Category = categoryRepository.getReferenceById(id)
        return categoryMapper.mapToCategoryDto(category)
    }

    @Transactional
    fun update(id: Long, dto: UpdateCategoryRequest) {
        val category: Category = categoryRepository.getReferenceById(id)
        category.name = dto.name
        category.description = dto.description
        categoryRepository.save(category)
    }

    @Transactional
    fun delete(id: Long) {
        categoryRepository.deleteById(id)
    }
}