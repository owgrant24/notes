package com.example.notes.core.notehistory.repo

import com.example.notes.core.notehistory.entity.NoteHistory
import org.springframework.data.jpa.repository.JpaRepository

interface NoteHistoryRepository : JpaRepository<NoteHistory, Long> {

}