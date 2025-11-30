package br.manogarrafa.biblioteca.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.manogarrafa.biblioteca.ui.components.CardList
import br.manogarrafa.biblioteca.ui.components.Search
import br.manogarrafa.biblioteca.ui.utils.Book
import br.manogarrafa.biblioteca.ui.utils.SearchByOption

@Composable
fun MainScreen(modifier: Modifier = Modifier, onBookClick: (Book) -> Unit) {
    var query by remember { mutableStateOf("") }
    var hasFilter by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(SearchByOption.values[0]) }
    var orderMode by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) // margem lateral
    ) {
        Spacer(Modifier.height(16.dp))
        Search(
            onSearch = {
                query = it
                hasFilter = it.isNotEmpty()
            },
            selected = selectedOption to { selectedOption = it },
            order = orderMode to { orderMode = it }
        )
        Spacer(Modifier.height(16.dp))
        CardList(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            hasFilter = hasFilter,
            orderMode = orderMode,
            query = query,
            filterStrategy = selectedOption.filterStrategy,
            onBookClick = { onBookClick(it) }
        )
    }
}