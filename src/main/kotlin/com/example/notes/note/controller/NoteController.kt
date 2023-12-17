package com.example.notes.note.controller

import com.example.notes.note.dto.CreateNoteRequest
import com.example.notes.note.dto.NoteDto
import com.example.notes.common.dto.PageResponse
import com.example.notes.note.dto.UpdateNoteRequest
import com.example.notes.note.service.NoteService
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/v1/notes")
class NoteController(
    private val noteService: NoteService
) {

    @PostMapping
    fun create(@Valid @RequestBody dto: CreateNoteRequest): Long = noteService.create(dto)

    @GetMapping
    fun getAll(): List<NoteDto> = noteService.getNoteAll()

    @GetMapping("/categories/{categoryId}")
    fun getAllByCategoryId(
        @PathVariable categoryId: Long,
        @RequestParam("page") pageIndex: Int?
    ): PageResponse<NoteDto> =
        noteService.getAllByCategoryId(categoryId, pageIndex)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): NoteDto = noteService.getNote(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: UpdateNoteRequest) = noteService.update(id, dto)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = noteService.delete(id)
}