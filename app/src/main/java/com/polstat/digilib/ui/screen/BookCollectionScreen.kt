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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.google.android.material.search.SearchBar
import com.polstat.digilib.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    var showClearIcon by rememberSaveable{ mutableStateOf(false) }
    if (query.text.isEmpty()) {
        showClearIcon = false
    } else if (query.text.isNotEmpty()) {
        showClearIcon = true
    }
    TextField(
                value = query,
                onValueChange = { newQuery ->
                    query = newQuery
                    if (query.text.isNotEmpty()) {
                        onSearch(query.text)
                    }
                },
                leadingIcon = { Icon(imageVector = Icons.Default.Search,
                    contentDescription = "") },
                placeholder = { Text("Enter keyword here...") },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    if (showClearIcon) {
                        IconButton(onClick = { query =
                            TextFieldValue("") }) {
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
fun BookList(books: List<Book>, onBookClick: (Book) -> Unit) {
    LazyColumn {
        items(books) { book ->
            BookItem(book = book, onBookClick = onBookClick)
        }
    }
}


@Composable
fun BookCollectionScreen(books: List<Book>, onBookClick: (Book)->Unit) {
    var filteredBooks = books
    var keyword by remember { mutableStateOf("") }
    if (keyword.isNotEmpty()) {
        filteredBooks = books.filter{ it.title.lowercase().contains(keyword.lowercase()) }
    }
    Column {
        SearchBar(onSearch = {
            keyword = it
        })
        BookList(books = filteredBooks, onBookClick=onBookClick)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookItem(book: Book, onBookClick: (Book) -> Unit) {
    Card(onClick = { onBookClick(book) }) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Box {
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
                Text(text = book.title,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
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
}
data class Book(val id:Int, val image: String, val title: String, val description: String)

fun dummyData():List<Book>{
    val bookDummies = mutableListOf<Book>()
    for (i in 1..30){
        val dummy = Book(
            id = i-1,
            image = "https://m.mediaamazon.com/images/I/61ZPNhC2hSL._SY522_.jpg",
            title = "Android Programming with Kotlin for Beginners Edition $i",
            description = "Description number" + i,
        )
        bookDummies.add(dummy)
    }
    return bookDummies
}

@Preview
@Composable
fun PreviewBookCollectionScreen(){
    val books = dummyData()
    BookCollectionScreen(books = books, onBookClick = {})
}