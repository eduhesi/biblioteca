package br.manogarrafa.biblioteca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.manogarrafa.biblioteca.navigation.AppNavHost
import br.manogarrafa.biblioteca.ui.components.CardItem
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme
import br.manogarrafa.biblioteca.ui.utils.Book

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BibliotecaTheme {
                AppNavHost()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    BibliotecaTheme {
        FlowRow(
            maxItemsInEachRow = 3,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            CardItem(
                book = Book(
                    id = 0,
                    title = "Vampeerz",
                    quantity = 5,
                    publisher = "",
                    price = 1.0,
                    publicationYear = 2020
                )
            )
            CardItem(
                book = Book(
                    id = 0,
                    title = "That time I got reincarned as a slime",
                    quantity = 21,
                    publisher = "",
                    price = 1.0,
                    publicationYear = 2020
                )
            )
            CardItem(
                book = Book(
                    id = 0,
                    title = "I prefer girls",
                    publisher = "",
                    price = 1.0,
                    publicationYear = 2020,
                    quantity = 1
                )
            )
        }
    }
}