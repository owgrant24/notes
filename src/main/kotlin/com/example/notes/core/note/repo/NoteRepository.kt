package com.example.notes.core.note.repo

import com.example.notes.core.note.entity.Note
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note, Long> {

    fun findByCategoryId(id: Long, pageable: Pageable) : Page<Note>
}