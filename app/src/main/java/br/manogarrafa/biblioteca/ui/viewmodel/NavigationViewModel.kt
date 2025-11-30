package br.manogarrafa.biblioteca.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.manogarrafa.biblioteca.ui.utils.Book

class NavigationViewModel : ViewModel() {
    private val _currentBook = MutableLiveData<Book?>()
    val currentBook: LiveData<Book?> get() = _currentBook

    fun selectBook(book: Book) {
        _currentBook.value = book
    }
}