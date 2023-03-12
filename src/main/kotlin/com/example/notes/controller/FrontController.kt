package com.example.notes.controller

import com.example.notes.dto.CategoryDto
import com.example.notes.dto.PageResponse
import com.example.notes.service.CategoryService
import com.example.notes.service.NoteService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class FrontController(
    private val categoryService: CategoryService,
    private val noteService: NoteService
) {

    @GetMapping
    fun getAll(model: Model, @RequestParam("page") pageIndex: Int?): String {
        val page: PageResponse<CategoryDto> = categoryService.getCategories(pageIndex)
        model.addAttribute("page", page)
        return "index"
    }

    @GetMapping("/notes/categories/{id}")
    fun getNotesByCategoryId(
        model: Model,
        @PathVariable("id") id: Long,
        @RequestParam("page") pageIndex: Int?
    ): String {
        val page = noteService.getAllByCategoryId(id, pageIndex)
        model.addAttribute("page", page)
        model.addAttribute("categoryId", id)
        return "notes"
    }

    @GetMapping("/notes/{id}")
    fun getNoteById(model: Model, @PathVariable("id") id: Long): String {
        model.addAttribute("note", noteService.getNote(id))
        return "note"
    }

    @GetMapping("/categories/{id}")
    fun getCategoryById(model: Model, @PathVariable("id") id: Long): String {
        model.addAttribute("category", categoryService.getCategory(id))
        return "category"
    }
}