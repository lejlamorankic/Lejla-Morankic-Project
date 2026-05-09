package presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.goaltrack.model.data.HardcodedData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AchievementsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AchievementsUiState())
    val uiState: StateFlow<AchievementsUiState> = _uiState

    init {
        loadAchievements()
    }

    private fun loadAchievements() {
        try {
            _uiState.update { it.copy(status = UiStatus.Loading) }

            _uiState.update {
                it.copy(
                    achievements = HardcodedData.sampleAchievements,
                    status = UiStatus.Success
                )
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(status = UiStatus.Error(e.message ?: "Unknown error"))
            }
        }
    }
}