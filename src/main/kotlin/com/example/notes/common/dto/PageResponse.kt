package com.example.notes.common.dto

import org.springframework.data.domain.Page

class PageResponse<T> private constructor(
    val content: List<T>,
    val data: Data
) {

    companion object {
        fun <T> from(page: Page<T>): PageResponse<T> {
            val data = Data(
                page.number,
                page.previousOrFirstPageable().pageNumber,
                page.nextOrLastPageable().pageNumber,
                page.isFirst,
                page.isLast,
                page.totalPages
            )
            return PageResponse(page.content, data)
        }
    }

    data class Data(
        val pageNumber: Int,
        val previousOrFirstPageNumber: Int,
        val nextOrLastPageNumber: Int,
        val isFirstPage: Boolean,
        val isLastPage: Boolean,
        val totalPages: Int,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PageResponse<*>

        if (content != other.content) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content.hashCode()
        result = 31 * result + data.hashCode()
        return result
    }

    override fun toString(): String {
        return "PageResponse(content=$content, data=$data)"
    }
}




