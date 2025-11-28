package br.manogarrafa.biblioteca.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.manogarrafa.biblioteca.ui.utils.Book
import br.manogarrafa.biblioteca.ui.utils.readFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface BooksUiState {
    object Error : BooksUiState
    object Loading : BooksUiState
    data class Success(val data: List<Book>) : BooksUiState
}

class BooksViewModel : ViewModel() {

    private val _data = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val data = _data.asStateFlow()

    fun fetchData(context: Context) = viewModelScope.launch {
        _data.value = BooksUiState.Loading
        try {
            readFile("colecao_estrangeira_completa.txt", context = context).collect { books ->
                books?.let {
                    _data.value = BooksUiState.Success(it)
                } ?: run {
                    _data.value = BooksUiState.Error
                }
            }
        } catch (e: Exception) {
            _data.value = BooksUiState.Error
        }
    }
}