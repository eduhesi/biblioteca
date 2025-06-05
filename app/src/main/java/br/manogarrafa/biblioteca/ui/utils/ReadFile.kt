package br.manogarrafa.biblioteca.ui.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

data class Book(
    val title: String,
    val publicationYear: Int?,
    val publisher: String,
    val price: Double,
    val quantity: Int,
    val editions: MutableList<Edition> = mutableListOf()
)

data class Edition(
    val number: Int?,
    val state: String
)


fun readFile(fileName: String, context: Context): MutableList<Book>? {
    return try {
        // Abre o arquivo da pasta assets
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))

        val yearRegex = Regex("\\((\\d{4})\\)")
        val books = mutableListOf<Book>()

        reader.useLines { lines ->
            lines.forEach { line ->
                when {
                    line.contains("Valor: R$") -> {
                        val parts = line.split(" /")
                        val titlePublisher = parts.first().trim()

                        val yearMatch = yearRegex.find(titlePublisher)
                        val publicationYear = yearMatch?.groupValues?.get(1)?.toIntOrNull()
                        val cleanTitle =
                            yearMatch?.let { titlePublisher.replace(it.value, "").trim() }
                                ?: titlePublisher

                        val publisher = parts.getOrNull(1)?.substringBefore("Valor:")?.trim() ?: ""
                        val price = parts.last()
                            .substringAfter("R$ ")
                            .substringBefore("\t")
                            .replace(".", "")
                            .replace(",", ".")
                            .toDouble()
                        val quantity =
                            parts.last().substringAfter("Quantidade:").substringBefore("Estado:")
                                .trim()
                                .toInt()

                        books.add(Book(cleanTitle, publicationYear, publisher, price, quantity))
                    }

                    line.startsWith(" nº") -> {
                        val number =
                            line.substringAfter(" nº").substringBefore("\t").trim().toIntOrNull()
                        val state = line.substringAfter("Estado:").trim()
                        books.lastOrNull()?.editions?.add(Edition(number, state))
                    }
                }
            }
        }
        books
    } catch (e: Exception) {
        e.printStackTrace()
        null // Retorna null em caso de erro, como arquivo não encontrado
    }
}

//fun showList() {
//    mangas.forEach { manga ->
//        println("Título: ${manga.title}")
//        println("Ano de Publicação: ${manga.publicationYear ?: "Não informado"}")
//        println("Editora: ${manga.publisher}")
//        println("Valor: R$ ${"%.2f".format(manga.price)}")
//        println("Quantidade: ${manga.quantity}")
//        if (manga.editions.isNotEmpty()) {
//            println("Edições:")
//            manga.editions.forEach { edition ->
//                println("  nº: ${edition.number ?: "-"} Estado: ${edition.state}")
//            }
//        }
//        println("---")
//    }
//}