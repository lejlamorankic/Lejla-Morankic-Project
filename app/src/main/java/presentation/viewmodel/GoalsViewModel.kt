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
class GoalsViewModel @Inject constructor(
    private val repository: GoalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GoalsUiState())
    val uiState: StateFlow<GoalsUiState> = _uiState

    init {
        loadGoals()
    }

    private fun loadGoals() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(status = UiStatus.Loading) }

                repository.getGoals().collect { goals ->
                    val searchText = _uiState.value.searchText

                    val filteredGoals = goals.filter {
                        it.name.contains(searchText, ignoreCase = true)
                    }

                    _uiState.update {
                        it.copy(
                            allGoals = goals,
                            filteredGoals = filteredGoals,
                            status = UiStatus.Success
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(status = UiStatus.Error(e.message ?: "Unknown error"))
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        val filteredGoals = _uiState.value.allGoals.filter {
            it.name.contains(text, ignoreCase = true)
        }

        _uiState.update {
            it.copy(
                searchText = text,
                filteredGoals = filteredGoals
            )
        }
    }

    fun startEditing(goal: Goal) {
        _uiState.update {
            it.copy(
                editingGoal = goal,
                editText = goal.name
            )
        }
    }

    fun onEditTextChange(text: String) {
        _uiState.update {
            it.copy(editText = text)
        }
    }

    fun saveEditedGoal() {
        val current = _uiState.value
        val oldGoal = current.editingGoal
        val newName = current.editText.trim()

        if (oldGoal == null || newName.isBlank()) return

        viewModelScope.launch {
            try {
                _uiState.update { it.copy(status = UiStatus.Loading) }

                repository.updateGoal(
                    oldGoal = oldGoal,
                    newGoal = Goal(name = newName)
                )

                _uiState.update {
                    it.copy(
                        editingGoal = null,
                        editText = "",
                        status = UiStatus.Success
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(status = UiStatus.Error(e.message ?: "Could not update goal"))
                }
            }
        }
    }

    fun cancelEditing() {
        _uiState.update {
            it.copy(
                editingGoal = null,
                editText = ""
            )
        }
    }

    fun deleteGoal(goal: Goal) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(status = UiStatus.Loading) }

                repository.deleteGoal(goal)

                _uiState.update { it.copy(status = UiStatus.Success) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(status = UiStatus.Error(e.message ?: "Could not delete goal"))
                }
            }
        }
    }
}