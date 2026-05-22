package presentation.ui.screens.goals.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goaltrack.model.Goal

@Composable
fun GoalOverviewItem(
    goal: Goal,
    onGoalClick: (String) -> Unit,
    onDeleteClick: (Goal) -> Unit,
    onEditClick: (Goal) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onGoalClick(goal.name) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = goal.name,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(
                    onClick = {
                        onEditClick(goal)
                    }
                ) {
                    Text("Edit")
                }

                Spacer(modifier = Modifier.padding(4.dp))

                Button(
                    onClick = {
                        onDeleteClick(goal)
                    }
                ) {
                    Text("Delete")
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}