package com.example.notes.category.controller

import com.example.notes.category.dto.CategoryDto
import com.example.notes.category.dto.CreateCategoryRequest
import com.example.notes.category.dto.UpdateCategoryRequest
import com.example.notes.category.service.CategoryService
import com.example.notes.common.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "Категории")
@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    @Operation(summary = "Создать категорию")
    @PostMapping
    fun create(@Valid @RequestBody dto: CreateCategoryRequest): Long = categoryService.create(dto)

    @Operation(summary = "Получить все категории")
    @GetMapping
    fun getAll(@RequestParam("page") pageIndex: Int?): PageResponse<CategoryDto> =
        categoryService.getCategories(pageIndex)

    @Operation(summary = "Получить категорию по идентификатору")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): CategoryDto = categoryService.getCategory(id)

    @Operation(summary = "Обновить категорию")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: UpdateCategoryRequest) = categoryService.update(id, dto)

    @Operation(summary = "Удалить категорию")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = categoryService.delete(id)
}