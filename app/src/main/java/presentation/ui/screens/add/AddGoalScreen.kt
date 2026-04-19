package presentation.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.add.components.AddGoalForm

@Composable
fun AddGoalScreen(
    onBackClick: () -> Unit
) {
    var goalText by remember { mutableStateOf("") }
    val isValid = goalText.isNotBlank()

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
            goalText = goalText,
            onGoalTextChange = { goalText = it },
            onSaveClick = onBackClick,
            onBackClick = onBackClick,
            isValid = isValid
        )
    }
}