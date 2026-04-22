package presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GoalDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GoalDetailUiState())
    val uiState: StateFlow<GoalDetailUiState> = _uiState

    fun setGoalDetails(goalName: String, goalStatus: String) {
        _uiState.update {
            it.copy(
                goalName = goalName,
                goalStatus = goalStatus,
                isLoaded = true
            )
        }
    }
}