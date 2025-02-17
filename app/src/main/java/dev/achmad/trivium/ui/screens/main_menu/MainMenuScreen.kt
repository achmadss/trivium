package dev.achmad.trivium.ui.screens.main_menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.achmad.trivium.R
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
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .height(48.dp),
            )
            Button(
                onClick = {
                    onClickPlay()
                },
                contentPadding = PaddingValues(
                    start = 6.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 14.dp
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    Icons.Outlined.PlayArrow,
                    contentDescription = "" // Add a valid content description
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Play")
            }
            Spacer(
                modifier = Modifier
                    .height(8.dp),
            )
            Button(
                onClick = {
                    onClickAchievements()
                },
                contentPadding = PaddingValues(
                    start = 6.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 14.dp
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    Icons.Outlined.StarBorder,
                    contentDescription = "" // Add a valid content description
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Achievements")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainMenuScreen()
}