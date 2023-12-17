package com.example.notes.note.controller

import com.example.notes.note.dto.NoteDto
import com.example.notes.common.dto.PageResponse
import com.example.notes.note.service.NoteService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class FrontNoteController(
        private val noteService: NoteService,
) {

    @GetMapping("/notes/categories/{id}")
    fun getNotesByCategoryId(
            model: Model,
            @PathVariable("id") id: Long,
            @RequestParam("page") pageIndex: Int?
    ): String {
        val page: PageResponse<NoteDto> = noteService.getAllByCategoryId(id, pageIndex)
        model.addAttribute("page", page)
        model.addAttribute("categoryId", id)
        return "notes"
    }

    @GetMapping("/notes/{id}")
    fun getNoteById(model: Model, @PathVariable("id") id: Long): String {
        model.addAttribute("note", noteService.getNote(id))
        return "note"
    }
}