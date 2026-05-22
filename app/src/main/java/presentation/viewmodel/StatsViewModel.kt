package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goaltrack.model.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val repository: GoalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        StatsUiState(
            badges = listOf(
                "Active",
                "Focused",
                "Consistent",
                "Motivated"
            )
        )
    )

    val uiState: StateFlow<StatsUiState> = _uiState

    init {
        loadStats()
    }

    private fun loadStats() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(status = UiStatus.Loading)
                }

                repository.getGoals().collect { goals ->
                    _uiState.update {
                        it.copy(
                            totalGoals = goals.size,
                            currentXP = goals.size * 20,
                            achievementsCount = if (goals.isEmpty()) 0 else 4,
                            status = UiStatus.Success
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        status = UiStatus.Error(
                            e.message ?: "Unknown error"
                        )
                    )
                }
            }
        }
    }
}