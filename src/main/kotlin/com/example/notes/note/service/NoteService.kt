package com.example.notes.note.service

import com.example.notes.common.dto.PageResponse
import com.example.notes.common.exception.AppException
import com.example.notes.common.util.definePageNumber
import com.example.notes.core.category.entity.Category
import com.example.notes.core.category.repo.CategoryRepository
import com.example.notes.core.note.entity.Note
import com.example.notes.core.note.repo.NoteRepository
import com.example.notes.core.notehistory.entity.NoteHistory
import com.example.notes.core.notehistory.entity.Status
import com.example.notes.core.notehistory.repo.NoteHistoryRepository
import com.example.notes.note.dto.CreateNoteRequest
import com.example.notes.note.dto.NoteDto
import com.example.notes.note.dto.UpdateNoteRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoteService(
        private val noteRepository: NoteRepository,
        private val categoryRepository: CategoryRepository,
        private val noteHistoryRepository: NoteHistoryRepository,
        private val noteMapper: NoteMapper,
        @Value(value = "\${general.note.page-size}") private val pageSize: Int,
) {

    @Transactional
    fun create(dto: CreateNoteRequest): Long {
        val categoryId = dto.categoryId!!
        val category: Category = categoryRepository.findById(categoryId)
                .orElseThrow { AppException("Category with id: $categoryId not found") }
        val note: Note = noteMapper.mapToNote(dto)
        note.category = category
        noteRepository.save(note)
        saveToHistory(note, Status.CREATED)
        return note.id ?: throw AppException("Invalid data")
    }

    @Transactional(readOnly = true)
    fun getNoteAll(): List<NoteDto> {
        return noteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .map { note -> noteMapper.mapToNoteDto(note) }
    }

    @Transactional(readOnly = true)
    fun getAllByCategoryId(categoryId: Long, pageIndex: Int?): PageResponse<NoteDto> {
        val pageNumber: Int = definePageNumber(pageIndex)
        val pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"))
        val page: Page<NoteDto> = noteRepository.findByCategoryId(categoryId, pageRequest)
                .map { note -> noteMapper.mapToNoteDto(note) }
        return PageResponse.from(page)
    }

    @Transactional(readOnly = true)
    fun getNote(id: Long): NoteDto {
        val note: Note = noteRepository.findById(id).orElseThrow { AppException("Note with id: $id not found") }
        return noteMapper.mapToNoteDto(note)
    }

    @Transactional
    fun update(id: Long, dto: UpdateNoteRequest) {
        val note: Note = noteRepository.findById(id).orElseThrow { AppException("Note with id: $id not found") }
        note.name = dto.name
        note.description = dto.description

        saveToHistory(note, Status.UPDATED)
        noteRepository.save(note)
    }

    @Transactional
    fun delete(id: Long) {
        noteRepository.deleteById(id)
    }

    private fun saveToHistory(note: Note, status: Status) {
        val noteHistory = NoteHistory()
        noteHistory.name = note.name
        noteHistory.description = note.description
        noteHistory.note = note
        noteHistory.status = status
        noteHistoryRepository.save(noteHistory)
    }
}