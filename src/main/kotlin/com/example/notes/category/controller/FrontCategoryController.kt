package com.example.notes.category.controller

import com.example.notes.category.dto.CategoryDto
import com.example.notes.category.service.CategoryService
import com.example.notes.common.dto.PageResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class FrontCategoryController(
        private val categoryService: CategoryService,
) {

    @GetMapping
    fun getAll(model: Model, @RequestParam("page") pageIndex: Int?): String {
        val page: PageResponse<CategoryDto> = categoryService.getCategories(pageIndex)
        model.addAttribute("page", page)
        return "index"
    }

    @GetMapping("/categories/{id}")
    fun getCategoryById(model: Model, @PathVariable("id") id: Long): String {
        model.addAttribute("category", categoryService.getCategory(id))
        return "category"
    }
}