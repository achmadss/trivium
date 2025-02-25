package dev.achmad.trivium.ui.screens.achievement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import dev.achmad.trivium.R
import dev.achmad.trivium.ui.components.TriviumTopBar
import dev.achmad.trivium.ui.components.achievement.TriviumAchievementListItem
import dev.achmad.trivium.ui.theme.background100
import kotlinx.serialization.Serializable

@Serializable
object AchievementRoute

fun NavGraphBuilder.achievement(
    onBack: () -> Unit,
) {
    composable<AchievementRoute> {
        AchievementScreen(onBack = onBack)
    }
}

@Composable
fun AchievementScreen(
    onBack: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TriviumTopBar(
                text = "Achievement",
                onBack = onBack
            )
        },
        containerColor = background100
    ) { contentPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(5) { count ->
                TriviumAchievementListItem(
                    title = stringResource(R.string.achievement_example, "$count"),
                    subtitle = "Example Text",
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