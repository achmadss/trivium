package dev.achmad.trivium.ui.screens.main_menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.achmad.trivium.R
import dev.achmad.trivium.ui.components.TriviumFilledButton
import dev.achmad.trivium.ui.theme.background100
import kotlinx.serialization.Serializable

@Serializable
object MainMenuRoute

fun NavGraphBuilder.mainMenu(
    onClickPlay: () -> Unit = {},
    onClickAchievements: () -> Unit = {}
) {
    composable<MainMenuRoute> {
        MainMenuScreen(
            onClickPlay,
            onClickAchievements
        )
    }
}

@Composable
fun MainMenuScreen(
    onClickPlay: () -> Unit = {},
    onClickAchievements: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background100),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.width(327.dp)
            )
            TriviumFilledButton(
                modifier = Modifier.width(160.dp),
                text = stringResource(R.string.main_menu_play),
                icon = Icons.Outlined.PlayArrow,
                onClick = onClickPlay
            )
            Spacer(modifier = Modifier.height(16.dp))
            TriviumFilledButton(
                modifier = Modifier.width(160.dp),
                text = stringResource(R.string.main_menu_achievement),
                icon = ImageVector.vectorResource(R.drawable.trophy),
                onClick = onClickAchievements
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainMenuScreen()
}