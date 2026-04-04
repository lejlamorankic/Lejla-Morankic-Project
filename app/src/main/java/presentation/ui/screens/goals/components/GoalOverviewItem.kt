package presentation.ui.screens.goals.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GoalOverviewItem(goalName: String) {
    Text(text = "• $goalName")
    Spacer(modifier = Modifier.height(8.dp))
}