package presentation.viewmodel

import com.example.goaltrack.model.Goal

data class GoalsUiState(
    val allGoals: List<Goal> = emptyList(),
    val searchText: String = "",
    val filteredGoals: List<Goal> = emptyList()
)