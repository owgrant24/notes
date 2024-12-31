package com.example.notes.note.controller

import com.example.notes.note.service.NoteFacadeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/notes")
class FrontNoteController(
    private val noteFacadeService: NoteFacadeService,
) {

    @GetMapping("/{id}")
    fun getNoteById(model: Model, @PathVariable("id") id: Long): String {
        model.addAttribute("note", noteFacadeService.getNote(id))
        return "note"
    }
}