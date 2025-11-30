package br.manogarrafa.biblioteca.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.manogarrafa.biblioteca.R
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme
import br.manogarrafa.biblioteca.ui.theme.TitleBackground
import br.manogarrafa.biblioteca.ui.theme.Typography
import br.manogarrafa.biblioteca.ui.utils.Book

@Composable
fun CardItem(modifier: Modifier = Modifier, book: Book) {
    val cardHeight = 250
    val cardWidth = cardHeight * 0.75
    val titleHeight = cardHeight * 0.20
    Box(
        modifier = modifier
            .size(width = cardWidth.dp, height = cardHeight.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.vampeerz),
            contentDescription = "Capa de ${book.title}",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                .height(titleHeight.dp)
                .background(TitleBackground)
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = book.title,
                textAlign = TextAlign.Center,
                style = Typography.titleMedium,
                maxLines = 2
            )
        }
        if (book.quantity > 1) {
            CardItemCounter(book.quantity, Modifier.padding(start = 8.dp, 8.dp))
        }
    }
}

@Composable
fun CardItemCounter(value: Int, modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(24.dp, 24.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Text(
            text = value.toString(),
            textAlign = TextAlign.Center,
            style = Typography.labelSmall,
            color = Color.Black
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    BibliotecaTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                listOf(
                    Book(
                        id = 0,
                        title = "Vampeerz",
                        quantity = 5,
                        publisher = "",
                        price = 1.0,
                        publicationYear = 2020
                    ), Book(
                        id = 0,
                        title = "That time I got reincarned as a slime",
                        quantity = 21,
                        publisher = "",
                        price = 1.0,
                        publicationYear = 2020
                    ), Book(
                        id = 0,
                        title = "I prefer girls",
                        publisher = "",
                        price = 1.0,
                        publicationYear = 2020,
                        quantity = 1
                    )
                )
            ) {
                CardItem(book = it)
            }
        }
    }
}