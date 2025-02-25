package dev.achmad.trivium.ui.screens.main_menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import dev.achmad.trivium.ui.components.TriviumFilledButtonState
import dev.achmad.trivium.ui.theme.triviumAccent
import dev.achmad.trivium.ui.theme.background100
import dev.achmad.trivium.ui.theme.background80
import dev.achmad.trivium.ui.theme.triviumSecondary
import kotlinx.serialization.Serializable

@Serializable
object MainMenuRoute

fun NavGraphBuilder.mainMenu(
    onClickPlay: () -> Unit = {},
    onClickAchievements: () -> Unit = {}
) {
    composable<MainMenuRoute> {
        MainMenuScreen(
            onClickPlay = onClickPlay,
            onClickAchievements = onClickAchievements,
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
            .background(background100)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(16.dp)
            ,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(background80)
                ,
                onClick = onClickAchievements
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.trophy),
                    contentDescription = null,
                    tint = triviumSecondary
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.width(327.dp)
            )
            TriviumFilledButton(
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
                border = BorderStroke(1.dp, triviumAccent),
                state = TriviumFilledButtonState.INACTIVE,
                icon = Icons.Outlined.PlayArrow,
                text = stringResource(R.string.main_menu_play),
                onClick = onClickPlay
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainMenuScreen()
}