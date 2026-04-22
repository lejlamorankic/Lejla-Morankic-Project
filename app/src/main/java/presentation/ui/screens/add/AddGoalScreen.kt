package presentation.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.add.components.AddGoalForm
import presentation.viewmodel.AddGoalViewModel

@Composable
fun AddGoalScreen(
    onBackClick: () -> Unit
) {
    val viewModel: AddGoalViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ScreenTopBar(
            title = "Add New Goal",
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        AddGoalForm(
            goalText = uiState.goalText,
            onGoalTextChange = { viewModel.onGoalTextChange(it) },
            onSaveClick = {
                if (viewModel.validateAndSave()) {
                    viewModel.clearForm()
                    onBackClick()
                }
            },
            onBackClick = onBackClick,
            isValid = uiState.isInputValid
        )

        uiState.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it)
        }
    }
}