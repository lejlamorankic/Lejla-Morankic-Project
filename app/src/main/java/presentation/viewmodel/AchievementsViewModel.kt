package presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.goaltrack.model.data.HardcodedData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AchievementsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        AchievementsUiState(
            achievements = HardcodedData.sampleAchievements
        )
    )

    val uiState: StateFlow<AchievementsUiState> = _uiState
}