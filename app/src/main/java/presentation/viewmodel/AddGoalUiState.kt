package presentation.viewmodel

data class AddGoalUiState(
    val goalText: String = "",
    val isInputValid: Boolean = false,
    val errorMessage: String? = null
)