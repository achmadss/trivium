package dev.achmad.trivium.ui.screens.achievement

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.achmad.core.model.achievement.TriviaAchievement
import dev.achmad.trivium.ui.components.achievement.preference.TriviumAchievementPreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class AchievementState(
    val unlockedAchievements: List<TriviaAchievement> = emptyList(),
    val lockedAchievements: List<TriviaAchievement> = emptyList(),
    val loading: Boolean = true
)

@HiltViewModel
class AchievementScreenViewModel @Inject constructor(
    private val preference: TriviumAchievementPreference
): ViewModel() {
    private val _state = MutableStateFlow(AchievementState())
    val state = _state.asStateFlow()

    fun getAchievements() {
        val unlockedAchievementIds = preference.collectedAchievements().get().mapNotNull { idString ->
            idString.toIntOrNull()
        }.toSet()

        val unlocked = TriviaAchievement.entries.filter { achievement ->
            unlockedAchievementIds.contains(achievement.id)
        }
        val locked = TriviaAchievement.entries.filter { achievement ->
            !unlockedAchievementIds.contains(achievement.id)
        }

        _state.update { currentState ->
            currentState.copy(
                unlockedAchievements = unlocked,
                lockedAchievements = locked,
                loading = false
            )
        }
    }
}