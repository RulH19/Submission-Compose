package id.com.jetbooks.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.com.jetbooks.components.BooksListItems
import id.com.jetbooks.components.CharacterHeader
import id.com.jetbooks.components.ScrollToTopButton
import id.com.jetbooks.components.SearchBar
import id.com.jetbooks.data.BookRepository
import id.com.jetbooks.ui.theme.JetBooksTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    bookViewModel: HomeViewModel = viewModel(factory = ViewModelFactory(BookRepository())),
    navigatetoDetail: (String) -> Unit,
) {
    val groupedBooks by bookViewModel.groupedBooks.collectAsState()
    val query by bookViewModel.query
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = bookViewModel::search,
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                )
            }
            groupedBooks.forEach { (initial, books) ->
                stickyHeader {
                    CharacterHeader(initial)
                }
                items(books, key = { it.id }) { book ->
                    BooksListItems(
                        name = book.name,
                        photoUrl = book.photoUrl,
                        sinopsis = book.sinopsis,
                        modifier = Modifier.clickable { navigatetoDetail(book.id) }
                    )
                }

            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(onClick = {
                scope.launch {
                    listState.scrollToItem(index = 0)
                }
            })

        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JetBooksTheme {
        HomeScreen(navigatetoDetail = {})
    }
}