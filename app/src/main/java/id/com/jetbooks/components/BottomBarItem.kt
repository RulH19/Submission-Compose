package id.com.jetbooks.components

import androidx.compose.ui.graphics.vector.ImageVector
import id.com.jetbooks.screen.Screen

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen,
    val contentDescription: String,
)
