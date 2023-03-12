package com.example.notes.service

import com.example.notes.db.entity.Note
import com.example.notes.db.repository.CategoryRepository
import com.example.notes.db.repository.NoteRepository
import com.example.notes.dto.CreateNoteRequest
import com.example.notes.dto.NoteDto
import com.example.notes.dto.UpdateNoteRequest
import com.example.notes.exception.AppException
import com.example.notes.mapper.NoteMapper
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoteService(
    private val noteRepository: NoteRepository,
    private val categoryRepository: CategoryRepository,
    private val noteMapper: NoteMapper
) {

    @Transactional
    fun create(dto: CreateNoteRequest): Long {
        val category = categoryRepository.getReferenceById(dto.categoryId!!)
        val note: Note = noteMapper.mapToNote(dto)
        note.category = category
        noteRepository.save(note)
        return note.id ?: throw AppException("Invalid data")
    }

    @Transactional
    fun getNoteAll(): List<NoteDto> {
        return noteRepository.findAll(Sort.by(Sort.Direction.ASC,"id"))
            .map { note -> noteMapper.mapToNoteDto(note) }
    }

    @Transactional
    fun getAllByCategoryId(categoryId: Long): List<NoteDto> {
        val notes = noteRepository.findByCategoryId(categoryId, Sort.by(Sort.Direction.ASC,"id"))
        return notes.map { note -> noteMapper.mapToNoteDto(note) }
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