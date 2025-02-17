package dev.achmad.trivium.ui.screens.main_menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object MainMenuRoute

fun NavGraphBuilder.mainMenu() {
    composable<MainMenuRoute> {
        MainMenuScreen()
    }
}

@Composable
fun MainMenuScreen() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column {
            
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainMenuScreen()
}