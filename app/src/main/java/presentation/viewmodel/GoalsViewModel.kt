package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goaltrack.model.repository.GoalRepository
import com.example.goaltrack.model.repository.GoalRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoalsViewModel : ViewModel() {

    private val repository: GoalRepository = GoalRepositoryImpl()

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
}