package br.manogarrafa.biblioteca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import br.manogarrafa.biblioteca.ui.components.CardItem
import br.manogarrafa.biblioteca.ui.theme.BibliotecaTheme
import br.manogarrafa.biblioteca.ui.utils.Book
import br.manogarrafa.biblioteca.ui.view.BookDetail
import br.manogarrafa.biblioteca.ui.view.MainScreen
import br.manogarrafa.biblioteca.ui.viewmodel.NavigationViewModel

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

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navViewModel: NavigationViewModel = viewModel()
    NavHost(navController, startDestination = "ListAll") {
        composable("ListAll") {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MainScreen(modifier = Modifier.padding(innerPadding)) { book ->
                    navViewModel.selectBook(book)
                    navController.navigate("BookDetails")
                }
            }
        }

        composable("BookDetails") {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                val book by navViewModel.currentBook.observeAsState()
                book?.let {
                    BookDetail(modifier = Modifier.padding(innerPadding), book = it)
                }
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
                    title = "Vampeerz",
                    quantity = 5,
                    publisher = "",
                    price = 1.0,
                    publicationYear = 2020
                )
            )
            CardItem(
                book = Book(
                    title = "That time I got reincarned as a slime",
                    quantity = 21,
                    publisher = "",
                    price = 1.0,
                    publicationYear = 2020
                )
            )
            CardItem(
                book = Book(
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