package presentation.ui.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import presentation.viewmodel.GoalDetailViewModel

@Composable
fun GoalDetailScreen(
    goalName: String,
    goalStatus: String,
    onBackClick: () -> Unit
) {
    val viewModel: GoalDetailViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(goalName, goalStatus) {
        viewModel.setGoalDetails(goalName, goalStatus)
    }

    GoalDetailContent(
        uiState = uiState,
        onBackClick = onBackClick
    )
}