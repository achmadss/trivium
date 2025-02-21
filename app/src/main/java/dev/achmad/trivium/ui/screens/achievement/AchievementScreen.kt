package dev.achmad.trivium.ui.screens.achievement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.achmad.trivium.ui.components.TriviumAchievementListItem
import dev.achmad.trivium.ui.components.TriviumTopBar
import dev.achmad.trivium.ui.theme.background100
import dev.achmad.trivium.R
import kotlinx.serialization.Serializable

@Serializable
object AchievementRoute

fun NavGraphBuilder.achievement(
) {
    composable<AchievementRoute> {
        AchievementScreen()
    }
}

@Composable
fun AchievementScreen(

) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TriviumTopBar(
                text = "Achievement",
                onBack = {}
            )
        }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(background100)
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(5) { count ->
                TriviumAchievementListItem(
                    title = stringResource(R.string.achievement_example, "$count"),
                    subtitle = "Example Text",
                    icon = ImageVector.vectorResource(R.drawable.trophy)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAchievementScreen() {
    AchievementScreen()
}