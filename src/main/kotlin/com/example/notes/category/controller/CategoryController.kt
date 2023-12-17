package com.example.notes.category.controller

import com.example.notes.category.dto.CategoryDto
import com.example.notes.category.dto.CreateCategoryRequest
import com.example.notes.category.dto.UpdateCategoryRequest
import com.example.notes.common.dto.*
import com.example.notes.category.service.CategoryService
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    @PostMapping
    fun create(@Valid @RequestBody dto: CreateCategoryRequest): Long = categoryService.create(dto)

    @GetMapping
    fun getAll(@RequestParam("page") pageIndex: Int?): PageResponse<CategoryDto> =
        categoryService.getCategories(pageIndex)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): CategoryDto = categoryService.getCategory(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: UpdateCategoryRequest) = categoryService.update(id, dto)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = categoryService.delete(id)
}