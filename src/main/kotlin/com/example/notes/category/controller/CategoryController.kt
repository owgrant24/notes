package com.example.notes.category.controller

import com.example.notes.category.dto.CategoryDto
import com.example.notes.category.dto.CategoryWithNotesResponse
import com.example.notes.category.dto.CreateCategoryRequest
import com.example.notes.category.dto.UpdateCategoryRequest
import com.example.notes.category.service.CategoryFacadeService
import com.example.notes.common.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "Категории")
@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val categoryFacadeService: CategoryFacadeService
) {

    @Operation(summary = "Создать категорию")
    @PostMapping
    fun create(@Valid @RequestBody dto: CreateCategoryRequest): Long = categoryFacadeService.create(dto)

    @Operation(summary = "Получить все категории")
    @GetMapping
    fun getAll(
        @RequestParam("page") pageIndex: Int?,
        @RequestParam("size", defaultValue = "15") size: Int?,
    ): PageResponse<CategoryDto> =
        categoryFacadeService.getCategories(pageIndex, size)

    @Operation(summary = "Получить категорию по идентификатору")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): CategoryDto = categoryFacadeService.getCategory(id)

    @Operation(summary = "Получить категорию со всеми заметками")
    @GetMapping("/{id}/notes")
    fun getCategoryWithNotes(
        @PathVariable id: Long,
        @RequestParam("page") pageIndex: Int?,
        @RequestParam("size", defaultValue = "15") size: Int?,
    ): CategoryWithNotesResponse =
        categoryFacadeService.getCategoryWithNotes(id, pageIndex, size)

    @Operation(summary = "Обновить категорию")
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: UpdateCategoryRequest,
    ) = categoryFacadeService.update(id, dto)

    @Operation(summary = "Удалить категорию")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = categoryFacadeService.delete(id)
}