package br.manogarrafa.biblioteca.ui.utils

import android.R as androidR

interface BookStrategy {
    fun filter(book: Book, query: String): Boolean
    fun sort(books: List<Book>, desc: Boolean = false): List<Book>
}

object NameStrategy : BookStrategy {
    override fun filter(book: Book, query: String) =
        book.title.contains(query, ignoreCase = true)

    override fun sort(books: List<Book>, desc: Boolean): List<Book> {
        val ascSorted = books.sortedBy { it.title.lowercase() }

        if (desc) {
            return ascSorted.asReversed()
        }

        return ascSorted
    }
}

object QuantityStrategy : BookStrategy {
    override fun filter(book: Book, query: String) =
        book.quantity.toString() == query

    override fun sort(books: List<Book>, desc: Boolean): List<Book> {
        val ascSorted = books.sortedWith(compareBy({ it.quantity }, { it.title }))

        if (desc) {
            return ascSorted.asReversed()
        }

        return ascSorted
    }
}

object PublishYearStrategy : BookStrategy {
    override fun filter(book: Book, query: String) =
        book.publicationYear?.toString() == query

    override fun sort(books: List<Book>, desc: Boolean): List<Book> {
        val ascSorted = books.sortedWith(compareBy({ it.publicationYear ?: 0 }, { it.title }))

        if (desc) {
            return ascSorted.asReversed()
        }

        return ascSorted
    }
}

sealed class SearchByOption(
    val iconRes: Int,
    val description: String,
    val filterStrategy: BookStrategy
) {
    object Name : SearchByOption(
        iconRes = androidR.drawable.ic_menu_crop,
        description = "Busca por nome",
        filterStrategy = NameStrategy
    )

    object Quantity : SearchByOption(
        iconRes = androidR.drawable.ic_menu_zoom,
        description = "Busca por quantidade",
        filterStrategy = QuantityStrategy
    )

    object PublishYear : SearchByOption(
        iconRes = androidR.drawable.ic_menu_my_calendar,
        description = "Busca por ano",
        filterStrategy = PublishYearStrategy
    )

    companion object {
        val values = listOf(Name, Quantity, PublishYear)
    }
}