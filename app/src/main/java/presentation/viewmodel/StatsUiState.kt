package presentation.viewmodel

data class StatsUiState(
    val totalGoals: Int = 0,
    val currentXP: Int = 0,
    val achievementsCount: Int = 0,
    val badges: List<String> = emptyList()
)