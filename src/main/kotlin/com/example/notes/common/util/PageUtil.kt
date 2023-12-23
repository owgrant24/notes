package com.example.notes.common.util

fun definePageNumber(pageIndex: Int?): Int {
    val pageNumber: Int = pageIndex ?: 1
    return if (pageNumber < 1) 1 else pageNumber
}