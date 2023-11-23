package com.polstat.digilib.ui.screen

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
/**
 * ViewModel for the Book Collection screen.
 */
class BookCollectionViewModel : ViewModel() {
    // MutableLiveData to store the original list of books.
    private val _originalBookList = MutableLiveData<List<Book>>()

    // MutableLiveData to store the current filtered list of books.
    private val _bookList = MutableLiveData<List<Book>>()

    // LiveData for observing the current list of books.
    val bookList: LiveData<List<Book>> get() = _bookList

    /**
     * Update the book list with a new list of books.
     * @param newBookList The new list of books to be set.
     */
    fun updateBookList(newBookList: List<Book>) {
        // Save the original list of books.
        _originalBookList.value = newBookList

        // If the current book list is empty, initialize it with the new list.
        if (_bookList.value.isNullOrEmpty()) {
            _bookList.value = newBookList
        }
    }

    /**
     * Filter the books based on the provided keyword and update the book list.
     * @param keyword The keyword to filter the books.
     */
    fun filterBooks(keyword: String) {
        // Retrieve the original list of books.
        val originalList = _originalBookList.value ?: emptyList()

        // Filter the books based on the keyword.
        val filteredList = if (keyword.isNotEmpty()) {
            originalList.filter { book ->
                // Check if the title contains the given keyword (case-insensitive).
                book.title.lowercase().contains(keyword.lowercase())
            }
        } else {
            // If the keyword is empty, return the original list.
            originalList
        }

        // Update the current book list with the filtered list.
        _bookList.value = filteredList
    }

    // MutableLiveData to store the current query in the search bar.
    private val _query = MutableLiveData(TextFieldValue(""))

    // LiveData for observing the current query in the search bar.
    val query: LiveData<TextFieldValue> get() = _query

    /**
     * Update the current query in the search bar.
     * @param newQuery The new query to be set.
     */
    fun updateQuery(newQuery: TextFieldValue) {
        _query.value = newQuery
    }
}
