package id.com.jetbooks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.com.jetbooks.ui.theme.JetBooksTheme

@Composable
fun BooksListItems(
    name: String,
    photoUrl: String,
    sinopsis: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(bottom = 12.dp)
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Column {
            Text(
                text = name,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(end = 16.dp),
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
            Text(
                text = sinopsis,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(end = 16.dp),
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun BooksListItemsPreview() {
    JetBooksTheme{
        BooksListItems(
            name = "Sagaras",
            photoUrl = "",
            sinopsis = "Sagaras")
    }
}