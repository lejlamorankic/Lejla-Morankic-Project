package presentation.ui.screens.goals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goaltrack.model.data.HardcodedData
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.goals.components.GoalOverviewItem

@Composable
fun GoalsScreen(
    onBackClick: () -> Unit,
    onGoalClick: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    val allGoals = HardcodedData.sampleGoals

    val filteredGoals = allGoals.filter {
        it.name.contains(searchText, ignoreCase = true)
    }

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
            onValueChange = { searchText = it },
            label = { Text("Search goals") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredGoals.isEmpty()) {
            Text("No items available")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredGoals) { goal ->
                    GoalOverviewItem(
                        goalName = goal.name,
                        onGoalClick = onGoalClick
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}