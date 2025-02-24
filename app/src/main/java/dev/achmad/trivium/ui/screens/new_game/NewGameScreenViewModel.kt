package dev.achmad.trivium.ui.screens.new_game

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.core.model.difficulty.TriviaDifficulty
import dev.achmad.core.model.mode.TriviaMode
import dev.achmad.core.model.type.TriviaType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class NewGameState(
    val loading: Boolean = false,
    val errorMessage: String = "",
    val category: TriviaCategory = TriviaCategory.RANDOM,
    val difficulty: TriviaDifficulty = TriviaDifficulty.EASY,
    val type: TriviaType = TriviaType.MULTIPLE_CHOICE,
    val mode: TriviaMode = TriviaMode.CASUAL,
)

@HiltViewModel
class NewGameScreenViewModel @Inject constructor (

): ViewModel() {

    private val _state = MutableStateFlow(NewGameState())
    val state = _state.asStateFlow()

    fun selectCategory(category: TriviaCategory) {
        _state.update { it.copy(category = category) }
    }

    fun selectDifficulty(difficulty: TriviaDifficulty) {
        _state.update { it.copy(difficulty = difficulty) }
    }

    fun selectMode(mode: TriviaMode) {
        _state.update { it.copy(mode = mode) }
    }

}