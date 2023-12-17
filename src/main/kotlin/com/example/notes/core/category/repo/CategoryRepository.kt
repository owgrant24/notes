package com.example.notes.core.category.repo

import com.example.notes.core.category.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>