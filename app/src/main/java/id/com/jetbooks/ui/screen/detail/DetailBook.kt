package id.com.jetbooks.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import id.com.jetbooks.data.BookRepository
import id.com.jetbooks.ui.theme.JetBooksTheme

@Composable
fun DetailBook(
    modifier: Modifier = Modifier,
    idBook: String,
    detailViewModel: DetailViewModel = viewModel(factory = DetailViewModelFactory(BookRepository())),
){
    val bookData by detailViewModel.getBookData(idBook).collectAsState()

    Scaffold{paddingValue ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValue)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = bookData.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
            Spacer(modifier = Modifier.padding(14.dp))
            Text(
                text = bookData.name,
                style = MaterialTheme.typography.h2,
                fontSize = 30.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            )
            Divider(
                color = MaterialTheme.colors.onBackground,
                thickness = 1.dp
            )
            Text(
                text = bookData.descripstion,
                lineHeight = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .alpha(0.7f)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JetBooksTheme {
        DetailBook(idBook = "1")
    }
}