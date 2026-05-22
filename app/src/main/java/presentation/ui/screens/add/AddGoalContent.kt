package presentation.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.add.components.AddGoalForm
import presentation.viewmodel.AddGoalUiState

@Composable
fun AddGoalContent(
    uiState: AddGoalUiState,
    onGoalTextChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit
) {
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
            onGoalTextChange = onGoalTextChange,
            onSaveClick = onSaveClick,
            onBackClick = onBackClick,
            isValid = uiState.isInputValid
        )

        uiState.errorMessage?.let { message ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = message)
        }
    }
}