package com.polstat.digilib.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.material.search.SearchBar
import com.polstat.digilib.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: BookCollectionViewModel, onSearch: (String) -> Unit) {
    // Observe the query LiveData from the ViewModel and get its state
    val query by viewModel.query.observeAsState(TextFieldValue(""))

    TextField(
        value = query,
        onValueChange = {
            // Update the query in the ViewModel and trigger the search callback
            viewModel.updateQuery(it)
            onSearch(it.text)
        },
        leadingIcon = {
            // Search icon
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        },
        placeholder = {
            // Placeholder text for the search bar
            Text("Enter keyword here...")
        },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            // Clear icon visible only when the query is not empty
            if (query.text.isNotEmpty()) {
                IconButton(
                    onClick = {
                        // Clear the query in the ViewModel and trigger the search callback
                        viewModel.updateQuery(TextFieldValue(""))
                        onSearch("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Clear Icon"
                    )
                }
            }
        }
    )
}

@Composable
fun BookList(books: List<Book>) {
    // Display a list of books using LazyColumn
    LazyColumn {
        items(books) { book ->
            BookItem(book)
        }
    }
}

@Composable
fun BookCollectionScreen() {
    // Create an instance of the BookCollectionViewModel
    val viewModel: BookCollectionViewModel = viewModel()

    // Observe the bookList LiveData from the ViewModel and get its state
    val books by viewModel.bookList.observeAsState(emptyList())

    // Local mutable state to hold the current book list
    var bookList by remember { mutableStateOf(emptyList<Book>()) }

    // Launch effect to update the bookList when the screen is launched
    LaunchedEffect(viewModel) {
        val dummyData = dummyData()
        viewModel.updateBookList(dummyData)
    }

    // Disposable effect to update the local state when the bookList changes
    DisposableEffect(books) {
        bookList = books
        onDispose {
            // Clean up, if needed
        }
    }

    // Compose the UI for the Book Collection screen
    Column {
        // Display the search bar with the provided ViewModel and search callback
        SearchBar(viewModel = viewModel, onSearch = { newKeyword ->
            viewModel.filterBooks(newKeyword)
        })

        // Display the list of books using the BookList composable
        BookList(books = bookList)
    }
}

@Composable
fun BookItem(book: Book) {
    // Compose the UI for a single book item
    Row(
        modifier= Modifier.padding(8.dp)
    ) {
        Box {
            // Display the book cover image
            Image(
                painter = painterResource(id = R.drawable.book1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            // Display the book title
            Text(
                text = book.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Display the book description
            Text(
                text = book.description,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// Data class representing a book
data class Book(val image: String, val title: String, val description: String)

// Function to generate dummy data (list of books)
fun dummyData(): List<Book> {
    val bookDummies = mutableListOf<Book>()
    for (i in 1..30) {
        val dummy = Book(
            image = "https://m.mediaamazon.com/images/I/61ZPNhC2hSL._SY522_.jpg",
            title = "Android Programming with Kotlin for Beginners Edition $i",
            description = "Description number " + i
        )
        bookDummies.add(dummy)
    }
    return bookDummies
}

// Preview composable for the Book Collection screen
@Preview
@Composable
fun PreviewBookCollectionScreen() {
    BookCollectionScreen()
}
