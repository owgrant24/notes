package com.example.notes.db.repository

import com.example.notes.db.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>