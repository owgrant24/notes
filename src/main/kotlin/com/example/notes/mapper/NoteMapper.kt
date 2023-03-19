package com.example.notes.mapper

import com.example.notes.db.entity.Note
import com.example.notes.dto.CreateNoteRequest
import com.example.notes.dto.NoteDto
import com.example.notes.exception.AppException
import com.example.notes.util.DATE_TIME_FORMATTER
import org.springframework.stereotype.Component

@Component
class NoteMapper {

    fun mapToNote(createNoteRequest: CreateNoteRequest): Note {
        val note = Note()
        note.name = createNoteRequest.name
        note.description = createNoteRequest.description
        return note
    }

    fun mapToNoteDto(note: Note): NoteDto {
        val id = note.id ?: throw AppException("Invalid data")
        val createdAt = DATE_TIME_FORMATTER.format(note.createdAt)
        val updatedAt = DATE_TIME_FORMATTER.format(note.updatedAt)
        return NoteDto(id, createdAt, updatedAt, note.name, note.description)
    }
}