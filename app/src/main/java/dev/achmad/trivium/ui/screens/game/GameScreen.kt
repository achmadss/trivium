package dev.achmad.trivium.ui.screens.game

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.Token
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.core.model.difficulty.TriviaDifficulty
import dev.achmad.core.model.mode.TriviaMode
import dev.achmad.core.model.type.TriviaType
import dev.achmad.trivium.ui.components.TriviumFilledButton
import dev.achmad.trivium.ui.components.TriviumFilledButtonState
import dev.achmad.trivium.ui.theme.accent
import dev.achmad.trivium.ui.theme.accentAlt
import dev.achmad.trivium.ui.theme.background100
import dev.achmad.trivium.ui.theme.background80
import dev.achmad.trivium.ui.theme.error
import dev.achmad.trivium.ui.theme.primaryDark
import dev.achmad.trivium.ui.theme.secondary
import dev.achmad.trivium.ui.theme.success
import dev.achmad.trivium.ui.theme.warning
import dev.achmad.trivium.ui.utils.activityViewModel
import dev.achmad.trivium.ui.utils.bottomBorder
import kotlinx.serialization.Serializable

@Serializable
data class GameRoute(
    val mode: TriviaMode,
    val difficulty: TriviaDifficulty,
    val category: TriviaCategory,
    val type: TriviaType,
)

fun NavGraphBuilder.game(
    onQuit: () -> Unit,
    onFinish: () -> Unit
) {
    composable<GameRoute> { backStackEntry ->
        val data = backStackEntry.toRoute<GameRoute>()
        val viewModel: GameScreenViewModel = activityViewModel()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.startGame(
                mode = data.mode,
                difficulty = data.difficulty,
                category = data.category,
                type = data.type
            )
//            viewModel.startGameMock()
        }

        GameScreen(
            state = state,
            onQuit = {
                viewModel.stopTimer()
                onQuit()
            },
            onSelectOption = { option ->
                viewModel.selectOption(option)
            },
            onFinish = {
                viewModel.stopTimer()
                onFinish()
            },
            onConfirmAnswer = {
                viewModel.onConfirm(data.difficulty)
            },
            onNextQuestion = {
                viewModel.onNextQuestion()
            }
        )
    }
}

@Composable
fun GameScreen(
    state: GameState = GameState(),
    onQuit: () -> Unit = {},
    onExit: () -> Unit = {},
    onConfirmAnswer: () -> Unit = {},
    onNextQuestion: () -> Unit = {},
    onSelectOption: (String) -> Unit = {},
    onFinish: () -> Unit = {}
) {
    if (state.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = primaryDark
            )
        }
        return
    }
    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        containerColor = background100,
        topBar = {
            TriviumGameAppBar(
                score = state.score,
                streak = state.streak,
                timeElapsed = state.timeElapsed,
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(16.dp)
                    .fillMaxSize()
                ,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Question ${state.currentQuestionIndex + 1}",
                    color = secondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 256.dp)
                        .background(background80)
                        .padding(16.dp)
                    ,
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = state.currentQuestion?.question ?: "",
                        color = secondary,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                }
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    state.currentOptions?.let {
                        items(state.currentOptions) { item ->
                            TriviumGameOptionButton(
                                text = item,
                                selected = state.selectedOption == item,
                                state = when {
                                    state.confirmed && item == state.selectedOption -> {
                                        if (item == state.currentQuestion?.correctAnswer) {
                                            TriviumGameOptionButtonState.CORRECT
                                        } else {
                                            TriviumGameOptionButtonState.INCORRECT
                                        }
                                    }
                                    state.confirmed && item == state.currentQuestion?.correctAnswer -> {
                                        TriviumGameOptionButtonState.CORRECT
                                    }
                                    else -> TriviumGameOptionButtonState.IDLE
                                },
                                onClick = {
                                    if (!state.confirmed) {
                                        onSelectOption(item)
                                    }
                                }
                            )
                        }
                    }
                }
                if (state.confirmed && state.selectedOption != null) {
                    val nextQuestionIndex = state.currentQuestionIndex + 1
                    TriviumFilledButton(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp),
                        text = if (nextQuestionIndex == state.questions.size) "Finish" else "Next Question",
                        border = BorderStroke(1.dp, accent),
                        state = TriviumFilledButtonState.INACTIVE,
                        onClick = {
                            if (nextQuestionIndex == state.questions.size) {
                                onFinish()
                            } else onNextQuestion()
                        }
                    )
                } else {
                    TriviumFilledButton(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp),
                        text = "Confirm",
                        onClick = onConfirmAnswer
                    )
                }
                TriviumFilledButton(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    border = BorderStroke(1.dp, error),
                    state = TriviumFilledButtonState.INACTIVE,
                    text = "Give Up",
                    onClick = onQuit,
                )
            }
        }
    }
}

@Composable
private fun TriviumGameAppBar(
    modifier: Modifier = Modifier,
    score: Int = 0,
    streak: Int = 0,
    timeElapsed: Int = 0,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .bottomBorder(1.dp, primaryDark)
            .background(background100)
            .statusBarsPadding()
            .padding(16.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Token,
                contentDescription = "Score",
                tint = accentAlt
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = score.toString(),
                color = accentAlt,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.LocalFireDepartment,
                contentDescription = "Questions remaining",
                tint = warning
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = streak.toString(),
                color = warning,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Timer,
                contentDescription = "Time remaining",
                tint = primaryDark
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = timeElapsed.toString(),
                color = primaryDark,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

enum class TriviumGameOptionButtonState {
    CORRECT, INCORRECT, IDLE
}

@Composable
private fun TriviumGameOptionButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    state: TriviumGameOptionButtonState = TriviumGameOptionButtonState.IDLE,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = when (state) {
            TriviumGameOptionButtonState.CORRECT -> success
            TriviumGameOptionButtonState.INCORRECT -> error
            else -> background80
        },
        border = BorderStroke(
            width = when {
                selected -> 1.dp
                else -> 0.dp
            },
            color = when {
                selected -> accent
                else -> Color.Transparent
            },
        )
    ) {
        Box(
            modifier = modifier
                .clickable { onClick() }
                .padding(16.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = secondary,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview
@Composable
private fun PreviewGameScreen() {
    GameScreen()
}