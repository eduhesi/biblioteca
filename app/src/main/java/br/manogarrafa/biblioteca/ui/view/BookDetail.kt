package br.manogarrafa.biblioteca.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.manogarrafa.biblioteca.R
import br.manogarrafa.biblioteca.ui.components.AddButton
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme
import br.manogarrafa.biblioteca.ui.utils.Book
import br.manogarrafa.biblioteca.ui.utils.Edition

@Composable
fun BookDetail(modifier: Modifier = Modifier, book: Book) {
    val cardHeight = 250
    val cardWidth = cardHeight * 0.75
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 72.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.vampeerz),
                    contentDescription = "Capa de ${book.title}",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .size(width = cardWidth.dp, height = cardHeight.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
            Text(
                "Informações",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(16.dp))
            // Tabela de informações
            Column {
                InfoRow(label = "Título", value = book.title)
                InfoRow(
                    label = "Ano de Publicação",
                    value = book.publicationYear?.toString() ?: "-"
                )
                InfoRow(label = "Editora", value = book.publisher)
                InfoRow(label = "Preço", value = "R$ %.2f".format(book.price))
                InfoRow(label = "Quantidade", value = book.quantity.toString())
                book.editions.forEach {
                    InfoRow(
                        label = "${book.title} - ${it.number?.toString() ?: "-"}",
                        value = it.state
                    )
                }
            }
        }

        AddButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) { }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookDetailPreview() {
    val book = Book(
        title = "Vampeerz",
        quantity = 5,
        publisher = "",
        price = 1.0,
        publicationYear = 2020,
        editions = mutableListOf(
            Edition(1, "Excelente"),
            Edition(2, "Excelente"),
            Edition(3, "Excelente"),
            Edition(4, "Excelente"),
            Edition(5, "Excelente"),
        )
    )
    BibliotecaTheme {
        BookDetail(book = book)
    }
}