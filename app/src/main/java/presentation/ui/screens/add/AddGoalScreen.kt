package presentation.ui.screens.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import presentation.viewmodel.AddGoalViewModel

@Composable
fun AddGoalScreen(
    onBackClick: () -> Unit
) {
    val viewModel: AddGoalViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AddGoalContent(
        uiState = uiState,
        onGoalTextChange = viewModel::onGoalTextChange,
        onSaveClick = {
            if (viewModel.validateAndSave()) {
                viewModel.clearForm()
                onBackClick()
            }
        },
        onBackClick = onBackClick
    )
}