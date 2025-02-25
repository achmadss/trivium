package dev.achmad.trivium.ui.screens.game

import android.util.Log
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GameState(
    val loading: Boolean = true,
    val timerRunning: Boolean = false,
    val errorMessage: String = "",
    val correctAnswerCount: Int = 0,
    val score: Int = 0,
    val highestStreak: Int = 0,
    val streak: Int = 0,
    val timeElapsed: Int = 0,
    val questions: List<GetTriviaResponse.Result> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedOption: String? = null,
    val currentQuestion: GetTriviaResponse.Result? = if (questions.isNotEmpty()) questions[currentQuestionIndex] else null,
    val currentOptions: List<String>? = currentQuestion?.options,
    val confirmed: Boolean = false,
)

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val openTriviaDatabaseRepository: OpenTriviaDatabaseRepository,
): ViewModel() {

    private var timerJob: Job? = null

    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    fun selectOption(option: String) {
        _state.update { it.copy(selectedOption = option) }
    }

    fun onConfirm(difficulty: TriviaDifficulty) {
        val isCorrect = state.value.selectedOption == state.value.currentQuestion?.correctAnswer

        _state.update { currentState ->
            currentState.copy(
                confirmed = true,
                score = if (isCorrect) {
                    currentState.score + when(difficulty) {
                        TriviaDifficulty.EASY -> 100
                        TriviaDifficulty.NORMAL -> 150
                        TriviaDifficulty.HARD -> 200
                    }
                } else currentState.score,
                streak = if (isCorrect) currentState.streak + 1 else 0,
                highestStreak = if (currentState.streak > currentState.highestStreak) currentState.streak else currentState.highestStreak,
                correctAnswerCount = if (isCorrect) currentState.correctAnswerCount + 1 else currentState.correctAnswerCount
            )
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = null
        timerJob = viewModelScope.launch {
            while (_state.value.timerRunning) {
                delay(1000)
                _state.update { it.copy(timeElapsed = it.timeElapsed + 1) }
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        _state.update { it.copy(timerRunning = false) }
    }

    fun onNextQuestion() {
        val nextQuestionIndex = state.value.currentQuestionIndex + 1
        val nextQuestion = state.value.questions[nextQuestionIndex]
        val nextOptions = nextQuestion.options

        _state.update { it.copy(
            currentQuestion = nextQuestion,
            currentQuestionIndex = nextQuestionIndex,
            currentOptions = nextOptions,
            confirmed = false
        ) }
    }

    fun startGame(
        mode: TriviaMode,
        difficulty: TriviaDifficulty,
        category: TriviaCategory,
        type: TriviaType,
    ) = viewModelScope.launch {
        try {
            _state.update { it.copy(loading = true) }
            val requestSessionTokenResult = openTriviaDatabaseRepository.requestSessionToken()
            if (requestSessionTokenResult is APICallResult.Error) {
                throw requestSessionTokenResult.error
            }
            when(mode) {
                TriviaMode.CASUAL -> {
                    val getTriviaResult = openTriviaDatabaseRepository.getTrivia(
                        amount = 20,
                        category = category,
                        difficulty = difficulty,
                        type = type
                    )
                    when(getTriviaResult) {
                        is APICallResult.Success -> {
                            val data = getTriviaResult.data.results.map {
                                it.copy(
                                    options = (it.incorrectAnswers + it.correctAnswer).shuffled()
                                )
                            }
                            _state.update {
                                it.copy(
                                    questions = data,
                                    currentQuestion = data.first(),
                                    currentOptions = data.first().options,
                                    currentQuestionIndex = 0,
                                    selectedOption = null,
                                    confirmed = false,
                                    streak = 0,
                                    score = 0,
                                )
                            }
                        }
                        is APICallResult.Error -> {
                            throw getTriviaResult.error
                        }
                    }
                }
                TriviaMode.TIME_ATTACK -> {
                    // TODO dika
                }
            }
            _state.update { it.copy(loading = false, timerRunning = true, timeElapsed = 0) }
            startTimer()
            Log.e("ASD", "Game Started")
        } catch (e: Exception) {
            _state.update {
                it.copy(errorMessage = e.message ?: unknownError, loading = false) }
            Log.e("ASD", "${e.message}")
        }
    }

}