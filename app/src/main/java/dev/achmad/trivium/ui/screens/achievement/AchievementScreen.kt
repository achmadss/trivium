package dev.achmad.trivium.ui.screens.achievement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.achmad.trivium.ui.components.TriviumTopBar
import dev.achmad.trivium.ui.components.achievement.TriviumAchievementListItem
import dev.achmad.trivium.ui.components.achievement.TriviumAchievementListItemState
import dev.achmad.trivium.ui.theme.background100
import dev.achmad.trivium.ui.theme.triviumPrimaryDark
import kotlinx.serialization.Serializable

@Serializable
object AchievementRoute

fun NavGraphBuilder.achievement(
    onBack: () -> Unit,
) {
    composable<AchievementRoute> {
        val viewModel: AchievementScreenViewModel = viewModel()
        val state by viewModel.state.collectAsState()

        AchievementScreen(
            state = state,
            onBack = onBack,
            onAchievementDisplay = {
                viewModel.getAchievements()
            }
        )
    }
}

@Composable
fun AchievementScreen(
    state: AchievementState = AchievementState(),
    onAchievementDisplay: () -> Unit = {},
    onBack: () -> Unit = {},
) {
    LaunchedEffect(Unit) { onAchievementDisplay() }

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
        if (state.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = triviumPrimaryDark
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.unlockedAchievements) { item ->
                    TriviumAchievementListItem(
                        title = stringResource(item.title),
                        subtitle = stringResource(item.description),
                        state = TriviumAchievementListItemState.ACTIVE
                    )
                }
                items(state.lockedAchievements) { item ->
                    TriviumAchievementListItem(
                        title = stringResource(item.title),
                        subtitle = stringResource(item.description),
                        state = TriviumAchievementListItemState.INACTIVE
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAchievementScreen() {
    AchievementScreen()
}