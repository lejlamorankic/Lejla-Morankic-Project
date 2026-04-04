package ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GoalInputSection(
    goalText: String,
    onGoalTextChange: (String) -> Unit,
    onAddClick: () -> Unit,
    isValid: Boolean
) {

    OutlinedTextField(
        value = goalText,
        onValueChange = onGoalTextChange,
        label = { Text("Enter goal") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(10.dp))

    Button(
        onClick = onAddClick,
        enabled = isValid
    ) {
        Text("Add Goal")
    }
}