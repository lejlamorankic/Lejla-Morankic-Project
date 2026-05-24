package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goaltrack.model.repository.GoalNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.data.remote.dto.GoalDto
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val repository: GoalNetworkRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NetworkUiState())
    val uiState: StateFlow<NetworkUiState> = _uiState

    fun loadGoalsFromNetwork() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        errorMessage = null,
                        message = "Loading goals from network..."
                    )
                }

                val goals = repository.getGoals()

                _uiState.update {
                    it.copy(
                        goals = goals,
                        isLoading = false,
                        message = "Network GET request successful"
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Network error",
                        message = "Network GET request failed"
                    )
                }
            }
        }
    }

    fun createNetworkGoal() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        errorMessage = null,
                        message = "Sending POST request..."
                    )
                }

                repository.createGoal(
                    GoalDto(
                        name = "Network Goal",
                        status = "Active",
                        category = "Cloud",
                        xp = 50
                    )
                )

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        message = "Network POST request successful"
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Network error",
                        message = "Network POST request failed"
                    )
                }
            }
        }
    }

    fun updateNetworkGoal() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        errorMessage = null,
                        message = "Sending PUT request..."
                    )
                }

                repository.updateGoal(
                    id = 1,
                    goal = GoalDto(
                        id = 1,
                        name = "Updated Network Goal",
                        status = "Completed",
                        category = "Cloud",
                        xp = 100
                    )
                )

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        message = "Network PUT request successful"
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Network error",
                        message = "Network PUT request failed"
                    )
                }
            }
        }
    }

    fun deleteNetworkGoal() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        errorMessage = null,
                        message = "Sending DELETE request..."
                    )
                }

                repository.deleteGoal(id = 1)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        message = "Network DELETE request successful"
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Network error",
                        message = "Network DELETE request failed"
                    )
                }
            }
        }
    }
}