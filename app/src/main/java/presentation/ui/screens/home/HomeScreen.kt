package presentation.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goaltrack.model.data.HardcodedData
import presentation.ui.screens.home.components.CategoryChip
import presentation.ui.screens.home.components.DashboardTitle
import presentation.ui.screens.home.components.GoalInputSection
import presentation.ui.screens.home.components.GoalList
import presentation.ui.screens.home.components.InfoRowData
import presentation.ui.screens.home.components.InfoSection
import presentation.ui.screens.home.components.UserProgressCard
import presentation.viewmodel.GoalViewModel

@Composable
fun HomeScreen(
    onNavigateToAdd: () -> Unit,
    onNavigateToDetails: (String) -> Unit,
    onNavigateToAchievements: () -> Unit,
    onNavigateToStats: () -> Unit,
    onNavigateToGoals: () -> Unit
) {
    val viewModel: GoalViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val categories = listOf("All") + HardcodedData.sampleCategories
    var selectedCategory by remember { mutableStateOf("All") }

    val achievements = HardcodedData.sampleAchievements

    val filteredGoals = uiState.goals.filter { goal ->
        when (selectedCategory) {
            "All" -> true
            "Study" -> goal.name.contains("study", ignoreCase = true) ||
                    goal.name.contains("read", ignoreCase = true)
            "Health" -> goal.name.contains("drink", ignoreCase = true) ||
                    goal.name.contains("water", ignoreCase = true)
            "Fitness" -> goal.name.contains("workout", ignoreCase = true) ||
                    goal.name.contains("exercise", ignoreCase = true)
            "Productivity" -> goal.name.contains("finish", ignoreCase = true) ||
                    goal.name.contains("assignment", ignoreCase = true)
            "Personal" -> goal.name.contains("personal", ignoreCase = true)
            else -> true
        }
    }

    val todayGoalRows = if (filteredGoals.isEmpty()) {
        listOf(InfoRowData("No goals available for this category"))
    } else {
        filteredGoals.map { goal ->
            InfoRowData(title = goal.name)
        }
    }

    val achievementRows = achievements.map { achievement ->
        InfoRowData(title = achievement)
    }

    val quickStatsRows = listOf(
        InfoRowData("Total Goals", uiState.totalGoals.toString()),
        InfoRowData("Current XP", uiState.currentXP.toString()),
        InfoRowData("Achievements", achievements.size.toString())
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        DashboardTitle(title = "GoalTrack")

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow {
            items(categories) { category ->
                CategoryChip(
                    title = category,
                    isSelected = selectedCategory == category,
                    onClick = {
                        selectedCategory = category
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToAdd) {
            Text("Go to Add Screen")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToAchievements) {
            Text("Go to Achievements")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToGoals) {
            Text("Go to Goals")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToStats) {
            Text("Go to Stats")
        }

        Spacer(modifier = Modifier.height(16.dp))

        UserProgressCard(
            levelNo = uiState.levelNo,
            levelTitle = uiState.levelTitle,
            currentXP = uiState.currentXP,
            maxXP = 100
        )

        Spacer(modifier = Modifier.height(16.dp))

        GoalInputSection(
            goalText = uiState.goalText,
            onGoalTextChange = { viewModel.onGoalTextChange(it) },
            onAddClick = { viewModel.addGoal() },
            isValid = uiState.isInputValid
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoSection(
            title = "Today's Goals",
            rows = todayGoalRows
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoSection(
            title = "Achievements",
            rows = achievementRows
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoSection(
            title = "Quick Stats",
            rows = quickStatsRows
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredGoals.isNotEmpty()) {
            GoalList(
                goals = filteredGoals,
                onGoalClick = { goalName ->
                    onNavigateToDetails(goalName)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}