package presentation.ui.screens.goals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goaltrack.model.Goal
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.goals.components.GoalOverviewItem

@Composable
fun GoalsContent(
    searchText: String,
    goals: List<Goal>,
    editingGoal: Goal?,
    editText: String,
    onSearchTextChange: (String) -> Unit,
    onEditTextChange: (String) -> Unit,
    onSaveEditClick: () -> Unit,
    onCancelEditClick: () -> Unit,
    onBackClick: () -> Unit,
    onGoalClick: (String) -> Unit,
    onDeleteClick: (Goal) -> Unit,
    onEditClick: (Goal) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ScreenTopBar(
            title = "All Goals",
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            label = { Text("Search goals") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (editingGoal != null) {
            Text(text = "Editing: ${editingGoal.name}")

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = editText,
                onValueChange = onEditTextChange,
                label = { Text("New goal name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(onClick = onSaveEditClick) {
                    Text("Save")
                }

                Spacer(modifier = Modifier.padding(4.dp))

                Button(onClick = onCancelEditClick) {
                    Text("Cancel")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (goals.isEmpty()) {
            Text("No items available")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(goals) { goal ->
                    GoalOverviewItem(
                        goal = goal,
                        onGoalClick = onGoalClick,
                        onDeleteClick = onDeleteClick,
                        onEditClick = onEditClick
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}