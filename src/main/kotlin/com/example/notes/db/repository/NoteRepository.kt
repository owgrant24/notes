package com.example.notes.db.repository

import com.example.notes.db.entity.Note
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note, Long> {

    fun findByCategoryId(id: Long, sort: Sort) : List<Note>
}