package com.example.notes.core.rest

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalControllerAdvice {
    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(e: Exception): ResponseEntity<String> {
        logger.error(e) { e.message }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
}