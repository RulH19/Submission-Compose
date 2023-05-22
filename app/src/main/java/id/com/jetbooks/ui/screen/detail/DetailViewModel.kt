package id.com.jetbooks.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.com.jetbooks.data.Book
import id.com.jetbooks.data.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(private val bookRepository: BookRepository): ViewModel() {
    fun getBookData(idBook: String): StateFlow<Book> = MutableStateFlow(bookRepository.getBooksId(idBook)).asStateFlow()

}
class DetailViewModelFactory(private val bookRepository: BookRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(bookRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}