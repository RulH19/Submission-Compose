package id.com.jetbooks

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.com.jetbooks.components.BooksListItems
import id.com.jetbooks.components.BottomBar
import id.com.jetbooks.components.CharacterHeader
import id.com.jetbooks.components.ScrollToTopButton
import id.com.jetbooks.components.SearchBar
import id.com.jetbooks.data.BookRepository
import id.com.jetbooks.data.BooksData
import id.com.jetbooks.ui.theme.JetBooksTheme
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun JetBooksApp(
    modifier: Modifier = Modifier,
) {
    Scaffold(bottomBar = { BottomBar() }) {
        TopBar()
    }

}

@Preview(showBackground = true)
@Composable
fun JetBooksAppPreview() {
    JetBooksTheme {
        JetBooksApp()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    bookViewModel: JetBooksViewModel = viewModel(factory = ViewModelFactory(BookRepository())),

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
            groupedBooks.forEach { (initial, heroes) ->
                stickyHeader {
                    CharacterHeader(initial)
                }
                items(heroes, key = { it.id }) { book ->
                    BooksListItems(
                        name = book.name,
                        photoUrl = book.photoUrl,
                        sinopsis = book.sinopsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 300))
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