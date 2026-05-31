package presentation.viewmodel

import model.data.remote.dto.GoalDto

data class NetworkUiState(
    val goals: List<GoalDto> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)