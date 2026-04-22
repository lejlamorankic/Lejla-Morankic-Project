package presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.goaltrack.model.data.HardcodedData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GoalsViewModel : ViewModel() {

    private val initialGoals = HardcodedData.sampleGoals

    private val _uiState = MutableStateFlow(
        GoalsUiState(
            allGoals = initialGoals,
            filteredGoals = initialGoals
        )
    )

    val uiState: StateFlow<GoalsUiState> = _uiState

    fun onSearchTextChange(text: String) {
        val filtered = _uiState.value.allGoals.filter {
            it.name.contains(text, ignoreCase = true)
        }

        _uiState.update {
            it.copy(
                searchText = text,
                filteredGoals = filtered
            )
        }
    }
}