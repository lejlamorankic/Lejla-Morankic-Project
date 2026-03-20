package ui.screens.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        LazyColumn {
            items(goals) { goal ->
                GoalItem(
                    goal = goal,
                    onGoalClick = onGoalClick
                )
            }
        }
    }
}