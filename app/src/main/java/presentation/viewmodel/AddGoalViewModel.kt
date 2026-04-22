package presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddGoalViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddGoalUiState())
    val uiState: StateFlow<AddGoalUiState> = _uiState

    fun onGoalTextChange(text: String) {
        _uiState.update {
            it.copy(
                goalText = text,
                isInputValid = text.isNotBlank(),
                errorMessage = null
            )
        }
    }

    fun validateAndSave(): Boolean {
        val current = _uiState.value

        return if (current.goalText.isBlank()) {
            _uiState.update {
                it.copy(
                    isInputValid = false,
                    errorMessage = "Goal cannot be empty"
                )
            }
            false
        } else {
            true
        }
    }

    fun clearForm() {
        _uiState.value = AddGoalUiState()
    }
}