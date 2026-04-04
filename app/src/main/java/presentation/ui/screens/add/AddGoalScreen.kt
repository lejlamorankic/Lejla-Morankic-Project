package presentation.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        Text(
            text = "Add New Goal",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        _root_ide_package_.presentation.ui.screens.add.components.AddGoalForm(
            goalText = goalText,
            onGoalTextChange = { goalText = it },
            onSaveClick = onBackClick,
            onBackClick = onBackClick,
            isValid = isValid
        )
    }
}