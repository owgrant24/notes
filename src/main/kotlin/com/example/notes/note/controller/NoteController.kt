package com.example.notes.note.controller

import com.example.notes.note.dto.CreateNoteRequest
import com.example.notes.note.dto.NoteDto
import com.example.notes.note.dto.UpdateNoteRequest
import com.example.notes.note.service.NoteFacadeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@Tag(name = "Заметки")
@RestController
@RequestMapping("/v1/notes")
class NoteController(
    private val noteFacadeService: NoteFacadeService
) {

    @Operation(summary = "Создать заметку")
    @PostMapping
    fun create(@Valid @RequestBody dto: CreateNoteRequest): Long = noteFacadeService.createNote(dto)

    @Operation(summary = "Получить все заметки")
    @GetMapping
    fun getAll(): List<NoteDto> = noteFacadeService.getNoteAll()

    @Operation(summary = "Получить заметку по идентификатору")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): NoteDto = noteFacadeService.getNote(id)

    @Operation(summary = "Обновить заметку")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: UpdateNoteRequest) = noteFacadeService.updateNote(id, dto)

    @Operation(summary = "Удалить заметку")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = noteFacadeService.deleteNote(id)
}