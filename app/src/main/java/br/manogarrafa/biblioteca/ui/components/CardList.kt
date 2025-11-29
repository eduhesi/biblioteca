package br.manogarrafa.biblioteca.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.manogarrafa.biblioteca.ui.utils.BookStrategy
import br.manogarrafa.biblioteca.ui.viewmodel.BooksUiState
import br.manogarrafa.biblioteca.ui.viewmodel.BooksViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardList(
    modifier: Modifier = Modifier,
    hasFilter: Boolean = false,
    orderMode: Boolean,
    query: String,
    filterStrategy: BookStrategy
) {
    val booksViewModel: BooksViewModel = viewModel()
    val context = LocalContext.current

    // Chama fetchData apenas uma vez quando o Composable entra em composição
    LaunchedEffect(Unit) {
        booksViewModel.fetchData(context)
    }

    val bookState by booksViewModel.data.collectAsState()
//    var currentPage by remember { mutableIntStateOf(0) }
    when (bookState) {
        is BooksUiState.Success -> {
            val data =
                filterStrategy.sort((bookState as BooksUiState.Success).data, desc = orderMode)

            val list = if (hasFilter) {
                data.filter { filterStrategy.filter(it, query) }
            } else {
                data
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
            ) {
                items(list) {
                    CardItem(it)
                }
//                itemsIndexed(list) { index, book ->
//                    CardItem(book)
//                    // Trigger fetch when 80% of the list is reached
//                    if (index >= (list.size * 0.8).toInt() && list.isNotEmpty()) {
//                        LaunchedEffect(currentPage) {
//                            currentPage += 1
//                            booksViewModel.fetchData(currentPage)
//                        }
//                    }
//                }
            }
        }

        is BooksUiState.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is BooksUiState.Error -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Erro")
            }
        }
    }
}