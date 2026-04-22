package presentation.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goaltrack.model.Goal

@Composable
fun GoalItem(
    goal: Goal,
    onGoalClick: (String) -> Unit
) {
    Text(
        text = "• ${goal.name}",
        modifier = Modifier.clickable {
            onGoalClick(goal.name)
        }
    )

    Spacer(modifier = Modifier.height(8.dp))
}