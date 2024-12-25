package com.example.notes.note.service

import com.example.notes.common.dto.PageResponse
import com.example.notes.common.exception.AppException
import com.example.notes.common.util.definePageNumber
import com.example.notes.core.category.entity.Category
import com.example.notes.core.category.service.CategoryService
import com.example.notes.core.note.entity.Note
import com.example.notes.core.note.service.NoteService
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
class NoteFacadeService(
        private val noteService: NoteService,
        private val categoryService: CategoryService,
        private val noteHistoryRepository: NoteHistoryRepository,
        private val noteMapper: NoteMapper,
        @Value(value = "\${general.note.page-size}") private val pageSize: Int,
) {

    @Transactional
    fun create(createNoteRequest: CreateNoteRequest): Long {
        val categoryId = createNoteRequest.categoryId!!
        val category: Category = categoryService.getById(categoryId)
        val note: Note = noteMapper.mapToNote(createNoteRequest)
        note.category = category
        noteService.save(note)
        saveToHistory(note, Status.CREATED)
        return note.id ?: throw AppException("Invalid data")
    }

    @Transactional(readOnly = true)
    fun getNoteAll(): List<NoteDto> {
        return noteService.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .map(noteMapper::mapToNoteDto)
    }

    @Transactional(readOnly = true)
    fun getAllByCategoryId(categoryId: Long, pageIndex: Int?, size: Int?): PageResponse<NoteDto> {
        val pageNumber: Int = definePageNumber(pageIndex)
        val pageRequest = PageRequest.of(pageNumber - 1, size ?: pageSize, Sort.by(Sort.Direction.ASC, "id"))
        val page: Page<NoteDto> = noteService.findByCategoryId(categoryId, pageRequest)
                .map(noteMapper::mapToNoteDto)
        return PageResponse.from(page)
    }

    @Transactional(readOnly = true)
    fun getNote(id: Long): NoteDto {
        val note: Note = noteService.getById(id)
        return noteMapper.mapToNoteDto(note)
    }

    @Transactional
    fun update(id: Long, updateNoteRequest: UpdateNoteRequest) {
        val note: Note = noteService.getById(id)
        note.name = updateNoteRequest.name
        note.description = updateNoteRequest.description

        saveToHistory(note, Status.UPDATED)
        noteService.save(note)
    }

    @Transactional
    fun delete(id: Long) {
        val note = noteService.getById(id);
        noteService.delete(note)
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