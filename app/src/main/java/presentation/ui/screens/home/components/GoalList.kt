package presentation.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.goaltrack.model.Goal

@Composable
fun GoalList(
    goals: List<Goal>,
    onGoalClick: (String) -> Unit
) {
    if (goals.isEmpty()) {
        Text("No goals yet")
    } else {
        Column {
            goals.forEach { goal ->
                _root_ide_package_.presentation.ui.screens.home.components.GoalItem(
                    goal = goal,
                    onGoalClick = onGoalClick
                )
            }
        }
    }
}