package presentation.viewmodel

import com.example.goaltrack.model.Goal

data class GoalUiState(
    val goals: List<Goal> = emptyList(),
    val goalText: String = "",
    val isInputValid: Boolean = false,
    val totalGoals: Int = 0,
    val currentXP: Int = 0,
    val levelNo: Int = 1,
    val levelTitle: String = "Beginner"
)