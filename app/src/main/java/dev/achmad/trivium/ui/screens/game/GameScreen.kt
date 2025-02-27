package dev.achmad.trivium.ui.screens.game

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.Timer
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.core.model.difficulty.TriviaDifficulty
import dev.achmad.core.model.mode.TriviaMode
import dev.achmad.core.model.type.TriviaType
import dev.achmad.trivium.R
import dev.achmad.trivium.ui.components.TriviumFilledButton
import dev.achmad.trivium.ui.components.TriviumFilledButtonState
import dev.achmad.trivium.ui.components.TriviumNonLazyGrid
import dev.achmad.trivium.ui.theme.background100
import dev.achmad.trivium.ui.theme.background60
import dev.achmad.trivium.ui.theme.background80
import dev.achmad.trivium.ui.theme.triviumAccent
import dev.achmad.trivium.ui.theme.triviumAccentAlt
import dev.achmad.trivium.ui.theme.triviumDisabledText
import dev.achmad.trivium.ui.theme.triviumError
import dev.achmad.trivium.ui.theme.triviumPrimaryDark
import dev.achmad.trivium.ui.theme.triviumSecondary
import dev.achmad.trivium.ui.theme.triviumSuccess
import dev.achmad.trivium.ui.theme.triviumWarning
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
    onNavigateToEnd: (
        score:Int,
        correctAnswerCount:Int,
        questionCount:Int,
        highestStreak:Int,
        timeElapsed:Int
            ) -> Unit
) {
    composable<GameRoute> { backStackEntry ->
        val data = backStackEntry.toRoute<GameRoute>()
        val viewModel: GameScreenViewModel = activityViewModel()
        val state by viewModel.state.collectAsState()

        GameScreen(
            state = state,
            onGiveUpRequest = {
                viewModel.pauseTimer()
            },
            onGiveUpConfirm = {
                viewModel.stopTimer()
                onQuit()
                viewModel.resetAll()
            },
            onGiveUpDeny = {
                viewModel.startTimer()
            },
            onSelectOption = { option ->
                viewModel.selectOption(option)
            },
            onNavigateToEnd = {
                viewModel.stopTimer()
                onNavigateToEnd(
                    state.score,
                    state.correctAnswerCount,
                    state.questions.size,
                    state.highestStreak,
                    state.timeElapsed
                )
            },
            onConfirmAnswer = {
                viewModel.onConfirm(data.difficulty)
            },
            onNextQuestion = {
                viewModel.onNextQuestion()
            },
            onStartGame = {
                viewModel.startGame(
                    mode = data.mode,
                    difficulty = data.difficulty,
                    category = data.category,
                    type = data.type
                )
            }
        )
    }
}

@Composable
fun GameScreen(
    state: GameState = GameState(),
    onGiveUpConfirm: () -> Unit = {},
    onGiveUpDeny: () -> Unit = {},
    onGiveUpRequest: () -> Unit = {},
    onConfirmAnswer: () -> Unit = {},
    onNextQuestion: () -> Unit = {},
    onSelectOption: (String) -> Unit = {},
    onNavigateToEnd: () -> Unit = {},
    onStartGame: () -> Unit = {}
) {
    var showGiveUpDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { onStartGame() }

    BackHandler(
        onBack = {
            if (showErrorDialog) {
                onGiveUpConfirm()
                return@BackHandler
            }
            if (showGiveUpDialog) {
                showGiveUpDialog = false
                onGiveUpRequest()
            }
            else {
                showGiveUpDialog = true
                onGiveUpRequest()
            }
        }
    )

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
        return
    }
    if (showErrorDialog) {
        TriviumGameMinimalDialog(
            icon = Icons.Outlined.Error,
            iconColor = triviumError,
            title = "Network Error",
            text = "Failed to fetch questions, please try again later",
            onDismissRequest = {
                onGiveUpConfirm()
                showErrorDialog = false
            },
            confirmText = "Retry",
            onConfirmRequest = {
                onStartGame()
                showErrorDialog = false
            },
        )
    }
    if (state.questions.isEmpty()) showErrorDialog = true
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
            modifier = Modifier
                .fillMaxSize(),
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
                    color = triviumSecondary,
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
                        color = triviumSecondary,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                }

                state.currentOptions?.let {
                    TriviumNonLazyGrid(
                        columns = 2,
                        itemCount = state.currentOptions.size,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) { index ->
                        val item = state.currentOptions[index]
                        TriviumGameOptionButton(
                            modifier = Modifier.fillMaxWidth(),
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
                val isLastQuestion = state.currentQuestionIndex + 1 == state.questions.size
                TriviumFilledButton(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    text = when {
                        !state.confirmed -> "Confirm"
                        isLastQuestion -> "Finish"
                        else -> "Next Question"
                    },
                    border = if (!state.confirmed && state.selectedOption != null) null else BorderStroke(1.dp, triviumAccent),
                    state = if (!state.confirmed && state.selectedOption != null) TriviumFilledButtonState.ACTIVE else TriviumFilledButtonState.INACTIVE,
                    onClick = when {
                        state.confirmed && isLastQuestion -> onNavigateToEnd
                        state.confirmed -> onNextQuestion
                        !state.confirmed && state.selectedOption != null -> onConfirmAnswer
                        else -> { {} }
                    }
                )
                if (!isLastQuestion) {
                    TriviumFilledButton(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp),
                        border = BorderStroke(1.dp, triviumError),
                        state = TriviumFilledButtonState.INACTIVE,
                        text = "Give Up",
                        onClick = {
                            showGiveUpDialog = true
                            onGiveUpRequest()
                        }
                    )
                }
                if (showGiveUpDialog) {
                    TriviumGameMinimalDialog(
                        icon = Icons.Outlined.QuestionMark,
                        iconColor = triviumWarning,
                        title = "Give Up",
                        text = "Are you sure you want to give up?",
                        dismissText = "No",
                        onDismissRequest = {
                            onGiveUpDeny()
                            showGiveUpDialog = false
                                           },
                        confirmText = "Yes",
                        onConfirmRequest = {
                            onGiveUpConfirm()
                            showGiveUpDialog = false
                        }
                    )
                }
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
            .bottomBorder(1.dp, triviumPrimaryDark)
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
                imageVector = ImageVector.vectorResource(R.drawable.editor_choice),
                contentDescription = "Score",
                tint = triviumAccentAlt
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = score.toString(),
                color = triviumAccentAlt,
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
                tint = triviumWarning
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = streak.toString(),
                color = triviumWarning,
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
                tint = triviumPrimaryDark
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = timeElapsed.toString(),
                color = triviumPrimaryDark,
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
            TriviumGameOptionButtonState.CORRECT -> triviumSuccess
            TriviumGameOptionButtonState.INCORRECT -> triviumError
            else -> background80
        },
        border = BorderStroke(
            width = when {
                selected -> 1.dp
                else -> 0.dp
            },
            color = when {
                selected -> triviumAccent
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
                color = triviumSecondary,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
private fun TriviumGameMinimalDialog(
    onDismissRequest: () -> Unit = {},
    dismissText: String? = null,
    onConfirmRequest: () -> Unit = {},
    confirmText: String? = null,
    icon: ImageVector,
    iconColor: Color,
    title: String,
    titleSize: TextStyle = MaterialTheme.typography.headlineLarge,
    text: String,
) {
    val density = LocalDensity.current
    val rowWidth = remember { mutableIntStateOf(0) }

    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Surface(
            border = BorderStroke(1.dp, background100),
            shape = RoundedCornerShape(8.dp),
            color = background80,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(background60)
                        .onGloballyPositioned { coordinates ->
                            rowWidth.intValue = coordinates.size.width
                        }
                        .padding(16.dp)
                    ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(36.dp)
                        ,
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        color = triviumSecondary,
                        style = titleSize,
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .width(
                            with(density) {
                                rowWidth.intValue.toDp()
                            }
                        )
                        .padding(horizontal = 8.dp),
                    text = text,
                    color = triviumDisabledText,
                    style = MaterialTheme.typography.labelLarge,
                )
                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .width(
                            with(density) {
                                rowWidth.intValue.toDp()
                            }
                        ),
                    horizontalArrangement = Arrangement.End
                ) {
                    dismissText?.let {
                        Box(
                            modifier = Modifier
                                .clickable { onDismissRequest() }
                                .clip(RoundedCornerShape(8.dp))
                                .background(triviumPrimaryDark)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = dismissText,
                                color = triviumSecondary,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    }
                    Spacer(Modifier.width(4.dp))
                    confirmText?.let {
                        Box(
                            modifier = Modifier
                                .clickable { onConfirmRequest() }
                                .clip(RoundedCornerShape(8.dp))
                                .background(triviumPrimaryDark)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = confirmText,
                                color = triviumSecondary,
                                style = MaterialTheme.typography.labelLarge,
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
private fun PreviewDialog() {
    TriviumGameMinimalDialog(
        icon = Icons.Outlined.NotificationsActive,
        iconColor = triviumWarning,
        title = "Time's Up",
        text = "Time has run out for your current session",
        dismissText = "OK",
        confirmText = "CONFIRM"
    )
}

//@Preview
//@Composable
//private fun PreviewGameScreen() {
//    GameScreen()
//}