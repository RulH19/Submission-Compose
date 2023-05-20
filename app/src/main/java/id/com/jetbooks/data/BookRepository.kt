package id.com.jetbooks.data

class BookRepository {
    fun getBooks(): List<Book>{
        return BooksData.books
    }
    fun searchBooks(query : String ): List<Book>{
        return BooksData.books.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}