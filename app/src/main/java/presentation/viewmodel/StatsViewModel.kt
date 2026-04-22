package presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.goaltrack.model.data.HardcodedData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StatsViewModel : ViewModel() {

    private val sampleGoals = HardcodedData.sampleGoals
    private val sampleAchievements = HardcodedData.sampleAchievements

    private val _uiState = MutableStateFlow(
        StatsUiState(
            totalGoals = sampleGoals.size,
            currentXP = sampleGoals.size * 20,
            achievementsCount = sampleAchievements.size,
            badges = listOf("Active", "Focused", "Consistent", "Motivated")
        )
    )

    val uiState: StateFlow<StatsUiState> = _uiState
}