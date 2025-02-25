package dev.achmad.trivium.ui.screens.end

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.Token
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import androidx.navigation.toRoute
import dev.achmad.core.model.achievement.TriviaAchievement
import dev.achmad.trivium.R
import dev.achmad.trivium.ui.components.TriviumFilledButton
import dev.achmad.trivium.ui.components.TriviumFilledButtonState
import dev.achmad.trivium.ui.components.achievement.TriviumAchievementListItem
import dev.achmad.trivium.ui.components.achievement.TriviumAchievementListItemState
import dev.achmad.trivium.ui.theme.triviumAccentAlt
import dev.achmad.trivium.ui.theme.background100
import dev.achmad.trivium.ui.theme.background80
import dev.achmad.trivium.ui.theme.triviumAccent
import dev.achmad.trivium.ui.theme.triviumDisabledText
import dev.achmad.trivium.ui.theme.triviumPrimaryDark
import dev.achmad.trivium.ui.theme.triviumSecondary
import dev.achmad.trivium.ui.theme.triviumSuccess
import dev.achmad.trivium.ui.theme.triviumWarning
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
data class EndRoute(
    val score: Int,
    val correctAnswerCount: Int,
    val questionCount: Int,
    val highestStreak: Int,
    val timeElapsed: Int,
)

fun NavGraphBuilder.end(
    onPlayAgain: () -> Unit,
    onNavigateToMainMenu: () -> Unit
) {
    composable<EndRoute> { backStackEntry ->
        val data = backStackEntry.toRoute<EndRoute>()

        EndScreen(
            score = data.score,
            correctAnswerCount = data.correctAnswerCount,
            questionCount = data.questionCount,
            highestStreak = data.highestStreak,
            timeElapsed = data.timeElapsed,
            onPlayAgain = onPlayAgain,
            onNavigateToMainMenu = onNavigateToMainMenu
        )
    }
}

val dummyUnlockedAchievements = listOf(
    // Easy achievements from different categories
    TriviaAchievement.KNOW_IT_ALL_NOVICE,
    TriviaAchievement.BOOKWORM_BEGINNER,
    TriviaAchievement.RHYTHM_ROOKIE,

    // Medium achievements
    TriviaAchievement.WORLD_EXPLORER,
    TriviaAchievement.CODE_WARRIOR,

    // Hard achievement
    TriviaAchievement.CINEMA_CONNOISSEUR,

    // Recently unlocked achievements (you might want to show these differently)
    TriviaAchievement.ANIME_APPRENTICE,
    TriviaAchievement.TECH_TRAINEE
)

@Composable
fun EndScreen(
    onPlayAgain: () -> Unit = {},
    onNavigateToMainMenu: () -> Unit = {},
    score: Int,
    correctAnswerCount: Int,
    questionCount: Int,
    highestStreak: Int,
    timeElapsed: Int
) {
    val accuracy = remember {
        derivedStateOf {
            correctAnswerCount
                .toFloat()
                .div(questionCount)
                .times(100)
                .roundToInt()
        }
    }.value

    Scaffold(
        modifier = Modifier,
        containerColor = background100,
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                TriviumFilledButton(
                    text = "Play Again",
                    onClick = onPlayAgain
                )
                TriviumFilledButton(
                    text = "Main Menu",
                    border = BorderStroke(1.dp, triviumAccent),
                    state = TriviumFilledButtonState.INACTIVE,
                    onClick = onNavigateToMainMenu
                )
            }
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
                    text = "Game Complete!",
                    color = triviumSecondary,
                    style = MaterialTheme.typography.headlineSmall
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.editor_choice),
                        contentDescription = "Score",
                        tint = triviumAccentAlt
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = score.toString(),
                        color = triviumAccentAlt,
                        style = MaterialTheme.typography.displayLarge
                    )
                }
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        TriviumEndScreenIconLabel(
                            title = "Time",
                            iconType = Icons.Outlined.Timer,
                            iconColor = triviumPrimaryDark,
                            content = "$timeElapsed s"
                        )
                    }
                    item {
                        TriviumEndScreenIconLabel(
                            title = "Accuracy",
                            iconType = ImageVector.vectorResource(R.drawable.target),
                            iconColor = dev.achmad.trivium.ui.theme.triviumError,
                            content = "$accuracy%"
                        )
                    }
                    item {
                        TriviumEndScreenIconLabel(
                            title = "Correct",
                            iconType = Icons.Outlined.CheckCircleOutline,
                            iconColor = triviumSuccess,
                            content = "$correctAnswerCount"
                        )
                    }
                    item {
                        TriviumEndScreenIconLabel(
                            title = "Best Streak",
                            iconType = Icons.Outlined.LocalFireDepartment,
                            iconColor = triviumWarning,
                            content = highestStreak.toString()
                        )
                    }
                }
                val unlockedAchievement = dummyUnlockedAchievements
                if (unlockedAchievement.isNotEmpty()) {
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = "Achievements Unlocked",
                        color = triviumSecondary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(unlockedAchievement) { item ->
                            TriviumAchievementListItem(
                                title = stringResource(id = item.title),
                                subtitle = stringResource(id = item.description),
                                state = TriviumAchievementListItemState.ACTIVE
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TriviumEndScreenIconLabel(
    title: String,
    iconType: ImageVector,
    iconColor: Color,
    content: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(background80)
            .padding(16.dp)
        ,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = iconType,
                    contentDescription = null,
                    tint = iconColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = title,
                    color = triviumDisabledText,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Text(
                text = content,
                color = triviumSecondary,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
private fun PreviewEndScreen() {
    EndScreen(
        score = 1400,
        correctAnswerCount = 14,
        questionCount = 21,
        highestStreak = 14,
        timeElapsed = 240
    )
}