package com.example.notes.controller

import com.example.notes.service.CategoryService
import com.example.notes.service.NoteService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class FrontController(
    private val categoryService: CategoryService,
    private val noteService: NoteService
) {

    @GetMapping
    fun getAll(model: Model): String {
        model.addAttribute("categories", categoryService.getCategories())
        return "index"
    }

    @GetMapping("/notes/categories/{id}")
    fun getNotesByCategoryId(model: Model, @PathVariable("id") id: Long): String {
        model.addAttribute("notes", noteService.getAllByCategoryId(id))
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