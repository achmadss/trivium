package dev.achmad.trivium.ui.screens.end

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.achmad.core.model.achievement.TriviaAchievement
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.core.model.difficulty.TriviaDifficulty
import dev.achmad.trivium.ui.components.achievement.preference.TriviumAchievementPreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class EndState(
    val achievementsObtained: List<TriviaAchievement> = emptyList(),
    val achievementProgress: List<TriviaAchievement> = emptyList()
)

@HiltViewModel
class EndScreenViewModel @Inject constructor(
    private val preference: TriviumAchievementPreference
) : ViewModel() {
    private val _state = MutableStateFlow(EndState())
    val state = _state.asStateFlow()

    fun achievementHandler(
        difficulty: TriviaDifficulty,
        category: TriviaCategory,
        correctAnswerCount: Int
    ) {
        if (correctAnswerCount == 20) {
            val achievements = TriviaAchievement.getByArgs(
                category = category,
                difficulty = difficulty
            ) // extra conditions

            val currentAchievements =
                preference.collectedAchievements().get().mapNotNull { idString ->
                    idString.toIntOrNull()
                }.toSet()
            val newAchievements = achievements.filter { achievement ->
                !currentAchievements.contains(achievement.id)
            }
            if (newAchievements.isNotEmpty()) {
                _state.update {
                    it.copy(
                        achievementsObtained = newAchievements
                    )
                }
                val updatedAchievements = currentAchievements.toMutableSet().apply {
                    addAll(newAchievements.map { it.id })
                }
                preference.collectedAchievements()
                    .set(updatedAchievements.map { it.toString() }.toSet())
            }
        } else {
            val achievements =
                TriviaAchievement.getByArgs(category = category, difficulty = difficulty)
            _state.update {
                it.copy(
                    achievementProgress = achievements
                )
            }
        }
    }
}