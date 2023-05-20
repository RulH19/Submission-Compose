package id.com.jetbooks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.com.jetbooks.screen.BottomBar
import id.com.jetbooks.screen.Screen
import id.com.jetbooks.ui.screen.favorite.FavoriteScreen
import id.com.jetbooks.ui.screen.home.HomeScreen
import id.com.jetbooks.ui.screen.profile.ProfileScreen
import id.com.jetbooks.ui.theme.JetBooksTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun JetBooksApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = { BottomBar(navController) },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
            composable(Screen.Favorite.route){
                FavoriteScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetBooksAppPreview() {
    JetBooksTheme {
        JetBooksApp()
    }
}
