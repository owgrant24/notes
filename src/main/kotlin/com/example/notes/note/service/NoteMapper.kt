package com.example.notes.note.service

import com.example.notes.core.note.entity.Note
import com.example.notes.note.dto.CreateNoteRequest
import com.example.notes.note.dto.NoteDto
import com.example.notes.common.exception.AppException
import com.example.notes.common.util.DATE_TIME_FORMATTER
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
        val id: Long = note.id ?: throw AppException("Invalid data")
        val createdAt: String = DATE_TIME_FORMATTER.format(note.createdAt)
        val updatedAt: String = DATE_TIME_FORMATTER.format(note.updatedAt)
        return NoteDto(id, createdAt, updatedAt, note.name, note.description)
    }
}