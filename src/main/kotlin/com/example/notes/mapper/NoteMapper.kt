package com.example.notes.mapper

import com.example.notes.db.entity.Note
import com.example.notes.dto.CreateNoteRequest
import com.example.notes.dto.NoteDto
import com.example.notes.exception.NoteException
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
        val id = note.id ?: throw NoteException("Invalid data")
        return NoteDto(id, note.createdAt, note.updatedAt, note.name, note.description)
    }
}