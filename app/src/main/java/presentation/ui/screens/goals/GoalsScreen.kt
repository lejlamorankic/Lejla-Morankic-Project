package presentation.ui.screens.goals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import presentation.viewmodel.GoalsViewModel

@Composable
fun GoalsScreen(
    onBackClick: () -> Unit,
    onGoalClick: (String) -> Unit
) {
    val viewModel: GoalsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GoalsContent(
        searchText = uiState.searchText,
        goals = uiState.filteredGoals,
        editingGoal = uiState.editingGoal,
        editText = uiState.editText,
        onSearchTextChange = viewModel::onSearchTextChange,
        onEditTextChange = viewModel::onEditTextChange,
        onSaveEditClick = viewModel::saveEditedGoal,
        onCancelEditClick = viewModel::cancelEditing,
        onBackClick = onBackClick,
        onGoalClick = onGoalClick,
        onDeleteClick = viewModel::deleteGoal,
        onEditClick = viewModel::startEditing
    )
}