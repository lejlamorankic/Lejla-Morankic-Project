package presentation.ui.screens.stats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import presentation.viewmodel.StatsViewModel

@Composable
fun StatsScreen(
    onBackClick: () -> Unit
) {
    val viewModel: StatsViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StatsContent(
        uiState = uiState,
        onBackClick = onBackClick
    )
}