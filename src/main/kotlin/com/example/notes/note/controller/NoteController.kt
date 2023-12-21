package com.example.notes.note.controller

import com.example.notes.note.dto.CreateNoteRequest
import com.example.notes.note.dto.NoteDto
import com.example.notes.common.dto.PageResponse
import com.example.notes.note.dto.UpdateNoteRequest
import com.example.notes.note.service.NoteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@Tag(name = "Заметки")
@RestController
@RequestMapping("/v1/notes")
class NoteController(
    private val noteService: NoteService
) {

    @Operation(summary = "Создать заметку")
    @PostMapping
    fun create(@Valid @RequestBody dto: CreateNoteRequest): Long = noteService.create(dto)

    @Operation(summary = "Получить все заметки")
    @GetMapping
    fun getAll(): List<NoteDto> = noteService.getNoteAll()

    @Operation(summary = "Получить все заметки определенной категории")
    @GetMapping("/categories/{categoryId}")
    fun getAllByCategoryId(
        @PathVariable categoryId: Long,
        @RequestParam("page") pageIndex: Int?
    ): PageResponse<NoteDto> =
        noteService.getAllByCategoryId(categoryId, pageIndex)

    @Operation(summary = "Получить заметку по идентификатору")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): NoteDto = noteService.getNote(id)

    @Operation(summary = "Обновить заметку")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: UpdateNoteRequest) = noteService.update(id, dto)

    @Operation(summary = "Удалить заметку")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = noteService.delete(id)
}