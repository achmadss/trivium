package dev.achmad.trivium.ui.screens.new_game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.core.model.difficulty.TriviaDifficulty
import dev.achmad.core.model.mode.TriviaMode
import dev.achmad.core.model.type.TriviaType
import dev.achmad.trivium.R
import dev.achmad.trivium.ui.components.TriviumFilledButton
import dev.achmad.trivium.ui.components.TriviumFilledButtonState
import dev.achmad.trivium.ui.components.TriviumTopBar
import dev.achmad.trivium.ui.theme.triviumAccent
import dev.achmad.trivium.ui.theme.background100
import dev.achmad.trivium.ui.theme.background80
import dev.achmad.trivium.ui.theme.triviumDisabled
import dev.achmad.trivium.ui.theme.triviumDisabledText
import dev.achmad.trivium.ui.theme.triviumPrimaryDark
import dev.achmad.trivium.ui.theme.triviumSecondary
import dev.achmad.trivium.ui.utils.activityViewModel
import kotlinx.serialization.Serializable

@Serializable
object NewGameRoute

fun NavGraphBuilder.newGame(
    onBack: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToGame: (TriviaMode, TriviaDifficulty, TriviaCategory, TriviaType) -> Unit,
) {
    composable<NewGameRoute> {
        val viewModel: NewGameScreenViewModel = activityViewModel()
        val state by viewModel.state.collectAsState()
        NewGameScreen(
            state = state,
            onBack = onBack,
            onNavigateToCategory = onNavigateToCategory,
            onSelectDifficulty = { difficulty ->
                viewModel.selectDifficulty(difficulty)
            },
            onSelectMode = { mode ->
                viewModel.selectMode(mode)
            },
            onStartGame = {
                onNavigateToGame(
                    state.mode,
                    state.difficulty,
                    state.category,
                    state.type
                )
            }
        )
    }
}

@Composable
fun NewGameScreen(
    state: NewGameState = NewGameState(),
    onBack: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onSelectDifficulty: (TriviaDifficulty) -> Unit = {},
    onSelectMode: (TriviaMode) -> Unit = {},
    onStartGame: () -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(state.errorMessage)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = background100,
        topBar = {
            TriviumTopBar(
                text = "New Game",
                onBack = onBack,
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(16.dp)
                    .fillMaxSize()
                ,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TriviumNewGameIconLabel(
                    text = "Category",
                    icon = ImageVector.vectorResource(R.drawable.bookmark)
                )
                TriviumNewGameTrailingFilledButton(
                    text = state.category.displayName,
                    onClick = onNavigateToCategory
                )
                TriviumNewGameIconLabel(
                    text = "Difficulty",
                    icon = ImageVector.vectorResource(R.drawable.bolt)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TriviaDifficulty.entries.forEach { difficulty ->
                        TriviumNewGameDifficultyButton(
                            modifier = Modifier
                                .weight(0.33f),
                            difficulty = difficulty,
                            state = when {
                                state.difficulty != difficulty -> TriviumNewGameDifficultyButtonState.INACTIVE
                                else -> TriviumNewGameDifficultyButtonState.ACTIVE
                            },
                            onClick = {
                                onSelectDifficulty(difficulty)
                            }

                        )
                    }
                }
                TriviumNewGameIconLabel(
                    text = "Mode",
                    icon = Icons.Outlined.Settings
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TriviaMode.entries.forEach { mode ->
                        TriviumFilledButton(
                            modifier = Modifier
                                .weight(0.5f),
                            text = stringResource(
                                when(mode) {
                                    TriviaMode.CASUAL -> dev.achmad.core.R.string.new_game_casual
                                    TriviaMode.TIME_ATTACK -> dev.achmad.core.R.string.new_game_time_attack
                                }
                            ),
                            icon = ImageVector.vectorResource(mode.icon),
                            state = when {
                                state.mode != mode -> TriviumFilledButtonState.INACTIVE
                                else -> TriviumFilledButtonState.ACTIVE
                            },
                            onClick = {
                                onSelectMode(mode)
                            }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(background80)
                        .defaultMinSize(
                            minHeight = 128.dp
                        )
                        .padding(16.dp)
                    ,
                ) {
                    Text(
                        text = stringResource(
                            when(state.mode) {
                                TriviaMode.CASUAL -> dev.achmad.core.R.string.new_game_casual_description
                                TriviaMode.TIME_ATTACK -> dev.achmad.core.R.string.new_game_time_attack_description
                            }
                        ),
                        color = triviumDisabledText,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                TriviumFilledButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Start Game",
                    icon = Icons.Outlined.PlayArrow,
                    state = TriviumFilledButtonState.INACTIVE,
                    border = BorderStroke(1.dp, triviumAccent),
                    contentPadding = PaddingValues(16.dp),
                    onClick = onStartGame,
                )
            }
            if (state.loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 80f))
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = triviumPrimaryDark
                    )
                }
            }
        }
    }
}

@Composable
private fun TriviumNewGameIconLabel(
    text: String,
    icon: ImageVector,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = null,
            tint = triviumPrimaryDark
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = triviumSecondary,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun TriviumNewGameTrailingFilledButton(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(background80)
            .clickable { onClick() }
            .padding(16.dp)
        ,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                color = triviumDisabledText,
                style = MaterialTheme.typography.labelLarge,
            )
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = null,
                tint = triviumDisabled
            )
        }
    }
}

enum class TriviumNewGameDifficultyButtonState {
    ACTIVE, INACTIVE
}

private data class TriviumNewGameDifficultyButtonColors(
    val backgroundColor: Color = background80,
    val buttonTextColor: Color = triviumSecondary,
    val iconColor: Color = triviumPrimaryDark,
)

@Composable
private fun TriviumNewGameDifficultyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    difficulty: TriviaDifficulty,
    state: TriviumNewGameDifficultyButtonState = TriviumNewGameDifficultyButtonState.INACTIVE
) {
    val colors = when(state) {
        TriviumNewGameDifficultyButtonState.ACTIVE -> {
            TriviumNewGameDifficultyButtonColors(
                backgroundColor = triviumPrimaryDark,
                buttonTextColor = triviumSecondary,
                iconColor = triviumSecondary,
            )
        }
        TriviumNewGameDifficultyButtonState.INACTIVE -> {
            TriviumNewGameDifficultyButtonColors(
                backgroundColor = background80,
                buttonTextColor = triviumDisabledText,
                iconColor = triviumDisabled,
            )
        }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colors.backgroundColor)
            .clickable { onClick() }
            .padding(16.dp)
        ,
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = difficulty.displayName,
                color = colors.buttonTextColor,
                style = MaterialTheme.typography.labelLarge
            )
            Row {
                when(difficulty) {
                    TriviaDifficulty.EASY -> {
                        Icon(
                            imageVector = Icons.Outlined.StarOutline,
                            contentDescription = null,
                            tint = colors.iconColor
                        )
                    }
                    TriviaDifficulty.NORMAL -> {
                        repeat(2) {
                            Icon(
                                imageVector = Icons.Outlined.StarOutline,
                                contentDescription = null,
                                tint = colors.iconColor
                            )
                        }
                    }
                    TriviaDifficulty.HARD -> {
                        repeat(3) {
                            Icon(
                                imageVector = Icons.Outlined.StarOutline,
                                contentDescription = null,
                                tint = colors.iconColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewNewGameScreen() {
    NewGameScreen()
}