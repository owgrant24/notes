package com.example.notes.core.notehistory.entity

import com.example.notes.core.note.entity.Note
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class NoteHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
    var name: String = ""
    var description: String? = null
    @Enumerated(EnumType.STRING)
    var status: Status = Status.CREATED
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "note_id")
    var note: Note? = null

}