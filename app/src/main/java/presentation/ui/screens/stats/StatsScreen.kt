package presentation.ui.screens.stats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import presentation.viewmodel.StatsViewModel

@Composable
fun StatsScreen(
    onBackClick: () -> Unit
) {
    val viewModel: StatsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StatsContent(
        uiState = uiState,
        onBackClick = onBackClick
    )
}