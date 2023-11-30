package com.polstat.digilib.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
fun BookDetailScreen(
    book: Book,
    onBackClick: () -> Unit,
    onShareClick: (String) -> Unit,
) {
    Column {
        Box {
            Image(
                painter = painterResource(id = R.drawable.book1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(text = book.title,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
        Text(text = book.description,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis
                )
        Row {
            Button(onClick = { onBackClick() }) {
                Text(text = "Back")
            }
            Button(onClick = { onShareClick(book.title) }) {
                        Text(text = "Share")
                    }
        }
    }
}

@Preview
@Composable
fun BookDetailScreenPreview() {

    // Create a dummy book for preview
    val dummyBook = Book(
        id = 1,
        image = "https://m.mediaamazon.com/images/I/61ZPNhC2hSL._SY522_.jpg",
        title = "Sample Book Title",
        description = "This is a sample book description."
    )

    // Call the actual BookDetailScreen with the dummy data
    BookDetailScreen(
        book = dummyBook,
        onBackClick = {},
        onShareClick = {}
    )
}