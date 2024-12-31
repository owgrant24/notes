package com.example.notes.category.controller

import com.example.notes.category.dto.CategoryDto
import com.example.notes.category.service.CategoryFacadeService
import com.example.notes.common.dto.PageResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class FrontCategoryController(
    private val categoryFacadeService: CategoryFacadeService,
) {

    @GetMapping
    fun getAll(
        model: Model,
        @RequestParam("page") pageIndex: Int?,
        @RequestParam("size", defaultValue = "15") size: Int?,
    ): String {
        val page: PageResponse<CategoryDto> = categoryFacadeService.getCategories(pageIndex, size)
        model.addAttribute("page", page)
        return "index"
    }

    @GetMapping("/categories/{id}")
    fun getCategoryById(
        model: Model,
        @PathVariable("id") id: Long,
    ): String {
        val category = categoryFacadeService.getCategory(id)
        model.addAttribute("category", category)
        return "category"
    }

    @GetMapping("/categories/{id}/notes")
    fun getCategoryWithNotes(
        model: Model,
        @PathVariable("id") id: Long,
        @RequestParam("page") pageIndex: Int?,
        @RequestParam("size", defaultValue = "15") size: Int?,
    ): String {
        val categoryWithNotes = categoryFacadeService.getCategoryWithNotes(id, pageIndex, size)
        model.addAttribute("page", categoryWithNotes.page)
        model.addAttribute("category", categoryWithNotes.category)
        return "notes"
    }
}