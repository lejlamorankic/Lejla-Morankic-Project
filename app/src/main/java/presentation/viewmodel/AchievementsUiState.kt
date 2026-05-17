package presentation.viewmodel

data class AchievementsUiState(
    val achievements: List<String> = emptyList(),
    val status: UiStatus = UiStatus.Init
)