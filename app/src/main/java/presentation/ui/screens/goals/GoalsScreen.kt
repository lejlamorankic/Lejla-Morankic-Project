package presentation.ui.screens.goals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import presentation.viewmodel.GoalsViewModel

@Composable
fun GoalsScreen(
    onBackClick: () -> Unit,
    onGoalClick: (String) -> Unit
) {
    val viewModel: GoalsViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GoalsContent(
        searchText = uiState.searchText,
        goals = uiState.filteredGoals,
        onSearchTextChange = viewModel::onSearchTextChange,
        onBackClick = onBackClick,
        onGoalClick = onGoalClick
    )
}