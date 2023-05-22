package id.com.jetbooks.data

class BookRepository {
    fun getBooks(): List<Book>{
        return BooksData.books
    }
    fun getBooksId(id :String) : Book =  BooksData.books.find{
        it.id == id
    } as Book
    fun searchBooks(query : String ): List<Book>{
        return BooksData.books.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}