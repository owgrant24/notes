package com.example.notes.note.controller

import com.example.notes.note.dto.NoteDto
import com.example.notes.common.dto.PageResponse
import com.example.notes.note.service.NoteFacadeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class FrontNoteController(
        private val noteFacadeService: NoteFacadeService,
) {

    @GetMapping("/notes/categories/{id}")
    fun getNotesByCategoryId(
            model: Model,
            @PathVariable("id") id: Long,
            @RequestParam("page") pageIndex: Int?,
            @RequestParam("size", defaultValue = "15") size: Int?,
    ): String {
        val page: PageResponse<NoteDto> = noteFacadeService.getAllByCategoryId(id, pageIndex, size)
        model.addAttribute("page", page)
        model.addAttribute("categoryId", id)
        return "notes"
    }

    @GetMapping("/notes/{id}")
    fun getNoteById(model: Model, @PathVariable("id") id: Long): String {
        model.addAttribute("note", noteFacadeService.getNote(id))
        return "note"
    }
}