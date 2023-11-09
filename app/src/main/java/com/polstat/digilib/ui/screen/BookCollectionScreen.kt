package com.polstat.digilib.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polstat.digilib.R

@Composable
fun BookCollectionScreen() {
    val books = dummyData()
    LazyColumn {
        items(books){book->
            BookItem(book)
        }
    }
}

@Composable
fun BookItem(book: Book){
    Row(
        modifier= Modifier.padding(8.dp)
    ) {
        Box{
            Image(
                painter = painterResource(id = R.drawable.book1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
            )
        }
        Column (
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Text(text = book.title,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = book.description,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
    }
}
data class Book(val imageResId: Int, val title: String, val description: String)
fun dummyData():List<Book>{
    val dummy = Book(
//        image= "https://m.mediaamazon.com/images/I/61ZPNhC2hSL._SY522_.jpg",
        imageResId = 0,
        title = "Android Programming with Kotlin for Beginners",
        description = "Android is the most popular mobile operating system in the world and Kotlin has been declared by Google as a first-class programming language to build Android apps. With the imminent arrival of the most anticipated Android update, Android 10 (Q), this book gets you started building apps compatible with the latest version of Android.")
        val bookDummies = mutableListOf<Book>()
    for (i in 1..30){
        bookDummies.add(dummy)
    }
    return bookDummies
}

@Preview
@Composable
fun PreviewBookCollectionScreen(){
    BookCollectionScreen()
}