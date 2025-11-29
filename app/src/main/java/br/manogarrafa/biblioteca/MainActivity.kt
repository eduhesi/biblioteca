package br.manogarrafa.biblioteca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.manogarrafa.biblioteca.ui.components.CardItem
import br.manogarrafa.biblioteca.ui.components.CardList
import br.manogarrafa.biblioteca.ui.components.Search
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme
import br.manogarrafa.biblioteca.ui.utils.Book
import br.manogarrafa.biblioteca.ui.utils.SearchByOption

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BibliotecaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(innerPadding)
                }
            }
        }
    }
}

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    var query by remember { mutableStateOf("") }
    var hasFilter by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(SearchByOption.values[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp) // margem lateral
    ) {
        Spacer(Modifier.height(16.dp))
        Search(
            onSearch = {
                query = it
                hasFilter = it.isNotEmpty()
            },
            onSelect = { selectedOption = it },
            selected = selectedOption
        )
        Spacer(Modifier.height(16.dp))
        CardList(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            hasFilter = hasFilter,
            query = query,
            filterStrategy = selectedOption.filterStrategy
        )
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
                Book(
                    title = "Vampeerz",
                    quantity = 5,
                    publisher = "",
                    price = 1.0,
                    publicationYear = 2020
                )
            )
            CardItem(
                Book(
                    title = "That time I got reincarned as a slime",
                    quantity = 21,
                    publisher = "",
                    price = 1.0,
                    publicationYear = 2020
                )
            )
            CardItem(
                Book(
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