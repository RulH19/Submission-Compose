package id.com.jetbooks.screen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailBook: Screen("home/{bookId}"){
        fun createRoute(bookId: String) = "home/${bookId}"
    }
}
