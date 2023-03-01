package com.example.notes.service

import com.example.notes.db.repository.NoteRepository
import com.example.notes.dto.CreateNoteRequest
import com.example.notes.dto.NoteDto
import com.example.notes.dto.UpdateNoteRequest
import com.example.notes.exception.NoteException
import com.example.notes.mapper.NoteMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoteService(
    private val noteRepository: NoteRepository,
    private val noteMapper: NoteMapper
) {

    @Transactional
    fun create(dto: CreateNoteRequest): Long {
        val note = noteMapper.mapToNote(dto)
        noteRepository.save(note)
        return note.id ?: throw NoteException("Invalid data")
    }

    @Transactional
    fun getNoteAll(): List<NoteDto> {
        return noteRepository.findAll()
            .map { note -> noteMapper.mapToNoteDto(note) }
    }

    @Transactional
    fun getNote(id: Long): NoteDto {
        val note = noteRepository.getReferenceById(id)
        return noteMapper.mapToNoteDto(note)
    }

    @Transactional
    fun update(id: Long, dto: UpdateNoteRequest) {
        val note = noteRepository.getReferenceById(id)
        note.name = dto.name
        note.description = dto.description
        noteRepository.save(note)
    }

    @Transactional
    fun delete(id: Long) {
        noteRepository.deleteById(id)
    }
}