package id.com.jetbooks.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.com.jetbooks.data.Book
import id.com.jetbooks.data.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _groupedBooks = MutableStateFlow(
        bookRepository.getBooks()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedBooks: StateFlow<Map<Char, List<Book>>> get() = _groupedBooks

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query

    fun search(newQuery : String){
        _query.value = newQuery
        _groupedBooks.value = bookRepository.searchBooks(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}

class ViewModelFactory(private val bookRepository: BookRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(bookRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}