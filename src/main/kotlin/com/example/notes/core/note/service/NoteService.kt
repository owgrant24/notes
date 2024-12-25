package com.example.notes.core.note.service

import com.example.notes.common.exception.AppException
import com.example.notes.core.note.entity.Note
import com.example.notes.core.note.repo.NoteRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class NoteService(
        private val noteRepository: NoteRepository
) {

    fun getById(id: Long): Note = noteRepository.findById(id).orElseThrow { AppException("Note with id: $id not found") }

    fun findByCategoryId(id: Long, pageable: Pageable) : Page<Note> = noteRepository.findByCategoryId(id, pageable)

    fun findAll(sort: Sort): List<Note> = noteRepository.findAll(sort)

    fun save(note: Note) = noteRepository.save(note)

    fun delete(note: Note) = noteRepository.delete(note)

}