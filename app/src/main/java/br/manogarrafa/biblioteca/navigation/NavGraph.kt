package br.manogarrafa.biblioteca.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.manogarrafa.biblioteca.ui.view.BookDetailView
import br.manogarrafa.biblioteca.ui.view.MainScreen
import kotlinx.serialization.Serializable

@Serializable
object ListAllScreen

@Serializable
data class BookDetailScreen(val id: Int)

@Composable
fun AppNavHost() {
//    val navViewModel: NavigationViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(navController, startDestination = ListAllScreen) {
        composable<ListAllScreen> {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MainScreen(modifier = Modifier.padding(innerPadding)) { book ->
//                    navViewModel.selectBook(book)
                    navController.navigate(BookDetailScreen(book.id))
                }
            }
        }

        composable<BookDetailScreen> { backStackEntry ->
            val bookDetailScreen: BookDetailScreen = backStackEntry.toRoute()
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                BookDetailView(bookDetailScreen.id, modifier = Modifier.padding(innerPadding))
//                val book by navViewModel.currentBook.observeAsState()
//                book?.let {
//                    BookDetail(modifier = Modifier.padding(innerPadding), book = it)
//                }
            }
        }
    }
}