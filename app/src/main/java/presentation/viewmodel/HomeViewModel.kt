package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goaltrack.model.Goal
import com.example.goaltrack.model.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GoalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadGoals()
    }

    private fun loadGoals() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(status = UiStatus.Loading)
                }

                repository.getGoals().collect { goals ->
                    updateStateWithGoals(goals)

                    _uiState.update {
                        it.copy(status = UiStatus.Success)
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

    fun onGoalTextChange(text: String) {
        _uiState.update {
            it.copy(
                goalText = text,
                isInputValid = text.isNotBlank()
            )
        }
    }

    fun onCategorySelected(category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
    }

    fun addGoal() {
        val currentState = _uiState.value

        if (currentState.goalText.isBlank()) return

        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(status = UiStatus.Loading)
                }

                repository.addGoal(
                    Goal(name = currentState.goalText)
                )

                _uiState.update {
                    it.copy(
                        goalText = "",
                        isInputValid = false,
                        status = UiStatus.Success
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        status = UiStatus.Error(
                            e.message ?: "Could not add goal"
                        )
                    )
                }
            }
        }
    }

    private fun updateStateWithGoals(
        goals: List<Goal>
    ) {
        val levelNo = (goals.size / 5) + 1

        _uiState.update {
            it.copy(
                goals = goals,
                totalGoals = goals.size,
                currentXP = goals.size * 20,
                levelNo = levelNo,
                levelTitle = getLevelTitle(levelNo)
            )
        }
    }

    private fun getLevelTitle(level: Int): String {
        return when (level) {
            1 -> "Beginner"
            2 -> "Goal Starter"
            3 -> "Focused Achiever"
            4 -> "Consistent Performer"
            else -> "Master Planner"
        }
    }
}