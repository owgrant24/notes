package com.example.notes.controller

import com.example.notes.dto.CreateNoteRequest
import com.example.notes.dto.NoteDto
import com.example.notes.dto.UpdateNoteRequest
import com.example.notes.service.NoteService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/notes")
class NoteController(
    private val noteService: NoteService
) {

    @PostMapping
    fun create(@RequestBody dto: CreateNoteRequest): Long = noteService.create(dto)

    @GetMapping
    fun getAll(): List<NoteDto> = noteService.getNoteAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): NoteDto = noteService.getNote(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: UpdateNoteRequest) = noteService.update(id, dto)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = noteService.delete(id)
}