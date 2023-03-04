package com.example.notes.db.repository

import com.example.notes.db.entity.Note
import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note, Long> {

    fun findNotesByCategoryId(id: Long): List<Note>
}