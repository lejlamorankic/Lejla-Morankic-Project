package presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.goaltrack.model.Goal
import com.example.goaltrack.model.data.HardcodedData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GoalViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        GoalUiState(
            goals = HardcodedData.sampleGoals,
            totalGoals = HardcodedData.sampleGoals.size,
            currentXP = HardcodedData.sampleGoals.size * 20,
            levelNo = (HardcodedData.sampleGoals.size / 5) + 1,
            levelTitle = getLevelTitle((HardcodedData.sampleGoals.size / 5) + 1)
        )
    )

    val uiState: StateFlow<GoalUiState> = _uiState

    fun onGoalTextChange(text: String) {
        _uiState.update {
            it.copy(
                goalText = text,
                isInputValid = text.isNotBlank()
            )
        }
    }

    fun addGoal() {
        val currentState = _uiState.value

        if (currentState.goalText.isBlank()) return

        val updatedGoals = currentState.goals + Goal(name = currentState.goalText)
        val updatedLevelNo = (updatedGoals.size / 5) + 1

        _uiState.update {
            it.copy(
                goals = updatedGoals,
                goalText = "",
                isInputValid = false,
                totalGoals = updatedGoals.size,
                currentXP = updatedGoals.size * 20,
                levelNo = updatedLevelNo,
                levelTitle = getLevelTitle(updatedLevelNo)
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