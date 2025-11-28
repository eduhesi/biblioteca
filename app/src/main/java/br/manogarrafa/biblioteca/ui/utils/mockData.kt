package br.manogarrafa.biblioteca.ui.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun mockData(page: Int = 0): Flow<List<Book>> = flow {
    fun generateItem(pos: Int): Book {
        return Book(
            title = "item - ${page * pos + pos}",
            publicationYear = 2025,
            publisher = "publisher",
            price = 20.0,
            quantity = 1
        )
    }

    val list = mutableListOf<Book>()
    repeat(10) { list.add(generateItem(it + 1)) }
    delay(1000L)
    emit(list)
}