package com.example.notes.category.service

import com.example.notes.category.dto.*
import com.example.notes.common.dto.PageResponse
import com.example.notes.common.exception.AppException
import com.example.notes.common.util.definePageNumber
import com.example.notes.core.category.entity.Category
import com.example.notes.core.category.service.CategoryService
import com.example.notes.core.note.service.NoteService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryFacadeService(
        private val categoryService: CategoryService,
        private val noteService: NoteService,
        private val categoryMapper: CategoryMapper,
        @Value(value = "\${general.default.page-size}") private val pageSize: Int
) {

    @Transactional
    fun create(dto: CreateCategoryRequest): Long {
        val category: Category = categoryMapper.mapToCategory(dto)
        categoryService.save(category)
        return category.id ?: throw AppException("Invalid data")
    }

    @Transactional(readOnly = true)
    fun getCategories(pageIndex: Int?, size: Int?): PageResponse<CategoryDto> {
        val pageNumber: Int = definePageNumber(pageIndex)
        val pageRequest = PageRequest.of(pageNumber - 1, size ?: pageSize, Sort.by(Sort.Direction.ASC, "id"))
        val page: Page<CategoryDto> = categoryService.findAll(pageRequest)
                .map(categoryMapper::mapToCategoryDto)
        return PageResponse.from(page)
    }

    @Transactional(readOnly = true)
    fun getCategory(id: Long): CategoryDto {
        val category: Category = categoryService.getById(id)
        return categoryMapper.mapToCategoryDto(category)
    }

    @Transactional(readOnly = true)
    fun getCategoryWithNotes(categoryId: Long, pageIndex: Int?, size: Int?): CategoryWithNotesResponse {
        val category: Category = categoryService.getById(categoryId)
        val categoryDto = categoryMapper.mapToCategoryDto(category)

        val pageNumber: Int = definePageNumber(pageIndex)
        val pageRequest = PageRequest.of(pageNumber - 1, size ?: pageSize, Sort.by(Sort.Direction.ASC, "id"))
        val page: Page<NoteDto> = noteService.findByCategoryId(categoryId, pageRequest)
            .map(categoryMapper::mapToNoteDto)
        val notePageResponse = PageResponse.from(page)
        return CategoryWithNotesResponse(categoryDto, notePageResponse)
    }

    @Transactional
    fun update(id: Long, dto: UpdateCategoryRequest) {
        val category: Category = categoryService.getById(id)
        category.name = dto.name
        category.description = dto.description
        categoryService.save(category)
    }

    @Transactional
    fun delete(id: Long) {
        val category = categoryService.getById(id)
        categoryService.delete(category)
    }
}