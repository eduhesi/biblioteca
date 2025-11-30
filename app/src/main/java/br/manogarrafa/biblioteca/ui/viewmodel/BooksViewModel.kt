package br.manogarrafa.biblioteca.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.manogarrafa.biblioteca.ui.utils.Book
import br.manogarrafa.biblioteca.ui.utils.readFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface BooksUiState<out T> {
    object Error : BooksUiState<Nothing>
    object Loading : BooksUiState<Nothing>
    data class Success<T>(val data: T) : BooksUiState<T>
}

class BooksViewModel : ViewModel() {

    private val _data = MutableStateFlow<BooksUiState<List<Book>>>(BooksUiState.Loading)
    val data = _data.asStateFlow()

    private fun getList(context: Context, callback: (List<Book>) -> Unit) = viewModelScope.launch {
        readFile("colecao_estrangeira_completa.txt", context = context).collect { books ->
            callback(books?.toList().orEmpty())
        }
    }

    fun fetchData(context: Context) = viewModelScope.launch {
        _data.value = BooksUiState.Loading
        try {
            getList(context) { books ->
                books.let {
                    _data.value = BooksUiState.Success(it)
                }
            }
        } catch (e: Exception) {
            _data.value = BooksUiState.Error
        }
    }

    private val _book = MutableStateFlow<BooksUiState<Book>>(BooksUiState.Loading)
    val book = _book.asStateFlow()

    fun fetchById(id: Int, context: Context) = viewModelScope.launch {
        _book.value = BooksUiState.Loading
        try {
            getList(context) { books ->
                val value = books.first { it.id == id }
                _book.value = BooksUiState.Success(value)
            }
        } catch (e: Exception) {
            _book.value = BooksUiState.Error
        }
    }
}