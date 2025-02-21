package dev.achmad.trivium.ui.screens.new_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.core.model.difficulty.TriviaDifficulty
import dev.achmad.core.model.mode.TriviaMode
import dev.achmad.core.model.type.TriviaType
import dev.achmad.core.network.APICallResult
import dev.achmad.core.unknownError
import dev.achmad.data.api.opentdb.model.response.trivia.GetTriviaResponse
import dev.achmad.data.api.opentdb.repository.OpenTriviaDatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    private val openTriviaDatabaseRepository: OpenTriviaDatabaseRepository,
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

    fun startGame(
        onSuccessAction: (List<GetTriviaResponse.Result>) -> Unit,
    ) = viewModelScope.launch {
        _state.update { it.copy(loading = true) }
        openTriviaDatabaseRepository.requestSessionToken()

        when(state.value.mode) {
            TriviaMode.CASUAL -> {
                val result = openTriviaDatabaseRepository.getTrivia(
                    amount = 20,
                    category = state.value.category,
                    difficulty = state.value.difficulty,
                    type = state.value.type
                )
                when(result) {
                    is APICallResult.Success -> {
                        onSuccessAction(result.data.results)
                    }
                    is APICallResult.Error -> {
                        _state.update { it.copy(errorMessage = result.error.message ?: unknownError) }
                    }
                }
            }
            TriviaMode.TIME_ATTACK -> {
                // TODO dika
            }
        }

        _state.update { it.copy(loading = false) }
    }

}