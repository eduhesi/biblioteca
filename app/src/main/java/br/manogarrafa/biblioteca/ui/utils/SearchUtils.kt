package br.manogarrafa.biblioteca.ui.utils

import android.R as androidR

interface BookFilterStrategy {
    fun filter(book: Book, query: String): Boolean
}

object NameFilter : BookFilterStrategy {
    override fun filter(book: Book, query: String) =
        book.title.contains(query, ignoreCase = true)
}

object QuantityFilter : BookFilterStrategy {
    override fun filter(book: Book, query: String) =
        book.quantity.toString() == query
}

object PublishYearFilter : BookFilterStrategy {
    override fun filter(book: Book, query: String) =
        book.publicationYear?.toString() == query
}

sealed class SearchByOption(
    val iconRes: Int,
    val description: String,
    val filterStrategy: BookFilterStrategy
) {
    object Name : SearchByOption(
        iconRes = androidR.drawable.ic_menu_crop,
        description = "Busca por nome",
        filterStrategy = NameFilter
    )

    object Quantity : SearchByOption(
        iconRes = androidR.drawable.ic_menu_zoom,
        description = "Busca por quantidade",
        filterStrategy = QuantityFilter
    )

    object PublishYear : SearchByOption(
        iconRes = androidR.drawable.ic_menu_my_calendar,
        description = "Busca por ano",
        filterStrategy = PublishYearFilter
    )

    companion object {
        val values = listOf(Name, Quantity, PublishYear)
    }
}