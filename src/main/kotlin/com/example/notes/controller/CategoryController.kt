package com.example.notes.controller

import com.example.notes.dto.*
import com.example.notes.service.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    @PostMapping
    fun create(@RequestBody dto: CreateCategoryRequest): Long = categoryService.create(dto)

    @GetMapping
    fun getAll(): List<CategoryDto> = categoryService.getCategories()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): CategoryDto = categoryService.getCategory(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: UpdateCategoryRequest) = categoryService.update(id, dto)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = categoryService.delete(id)
}