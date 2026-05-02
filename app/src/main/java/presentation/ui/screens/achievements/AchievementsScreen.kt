package presentation.ui.screens.achievements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import presentation.viewmodel.AchievementsViewModel

@Composable
fun AchievementsScreen(
    onBackClick: () -> Unit
) {
    val viewModel: AchievementsViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AchievementsContent(
        uiState = uiState,
        onBackClick = onBackClick
    )
}