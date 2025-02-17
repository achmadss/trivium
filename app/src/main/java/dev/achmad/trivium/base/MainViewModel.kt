package dev.achmad.trivium.base

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

sealed interface MainState {
    data object Loading: MainState
    data class Success(val count: Int = 0): MainState
}

@HiltViewModel
class MainViewModel @Inject constructor(
    basePreferences: BasePreferences,
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val state = _state.asStateFlow()

    private val _newIntent = MutableStateFlow<Intent?>(null)
    val newIntent = _newIntent.asStateFlow()

    val appTheme = basePreferences
        .appTheme()
        .stateIn(viewModelScope)

    val dynamicColors = basePreferences
        .dynamicColors()
        .stateIn(viewModelScope)

    fun onNewIntent(intent: Intent) = _newIntent.update { intent }
    fun resetIntent() = _newIntent.update { null }

}