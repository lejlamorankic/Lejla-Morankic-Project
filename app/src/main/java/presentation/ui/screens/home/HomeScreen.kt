package presentation.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goaltrack.model.Goal
import com.example.goaltrack.viewmodel.GoalViewModel
import presentation.ui.screens.home.components.DashboardTitle
import presentation.ui.screens.home.components.GoalInputSection
import presentation.ui.screens.home.components.GoalList
import presentation.ui.screens.home.components.InfoRowData
import presentation.ui.screens.home.components.InfoSection
import presentation.ui.screens.home.components.UserProgressCard

@Composable
fun HomeScreen(
    onNavigateToAdd: () -> Unit,
    onNavigateToDetails: (String) -> Unit,
    onNavigateToAchievements: () -> Unit,
    onNavigateToStats: () -> Unit,
    onNavigateToGoals: () -> Unit
) {
    val viewModel = remember { GoalViewModel() }
    var goalText by remember { mutableStateOf("") }
    val goals = viewModel.getGoals()

    val achievements = listOf(
        "First Goal Added",
        "Getting Started",
        "Future Achiever"
    )

    val levelNo = 2
    val levelTitle = "Goal Starter"
    val currentXP = goals.size * 20
    val maxXP = 100

    val todayGoalRows = if (goals.isEmpty()) {
        listOf(_root_ide_package_.presentation.ui.screens.home.components.InfoRowData("No goals added yet"))
    } else {
        goals.map { goal ->
            _root_ide_package_.presentation.ui.screens.home.components.InfoRowData(title = goal.name)
        }
    }

    val achievementRows = achievements.map { achievement ->
        _root_ide_package_.presentation.ui.screens.home.components.InfoRowData(title = achievement)
    }

    val quickStatsRows = listOf(
        _root_ide_package_.presentation.ui.screens.home.components.InfoRowData(
            "Total Goals",
            goals.size.toString()
        ),
        _root_ide_package_.presentation.ui.screens.home.components.InfoRowData(
            "Current XP",
            currentXP.toString()
        ),
        _root_ide_package_.presentation.ui.screens.home.components.InfoRowData(
            "Achievements",
            achievements.size.toString()
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        _root_ide_package_.presentation.ui.screens.home.components.DashboardTitle(title = "GoalTrack")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToAdd) {
            Text("Go to Add Screen")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToAchievements) {
            Text("Go to Achievements")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToGoals) {
            Text("Go to Goals")
        }

        Button(onClick = onNavigateToStats) {
            Text("Go to Stats")
        }

        Spacer(modifier = Modifier.height(16.dp))

        _root_ide_package_.presentation.ui.screens.home.components.UserProgressCard(
            levelNo = levelNo,
            levelTitle = levelTitle,
            currentXP = currentXP,
            maxXP = maxXP
        )

        Spacer(modifier = Modifier.height(16.dp))

        _root_ide_package_.presentation.ui.screens.home.components.GoalInputSection(
            goalText = goalText,
            onGoalTextChange = { goalText = it },
            onAddClick = {
                if (goalText.isNotBlank()) {
                    viewModel.addGoal(Goal(name = goalText))
                    goalText = ""
                }
            },
            isValid = goalText.isNotBlank()
        )

        Spacer(modifier = Modifier.height(16.dp))

        _root_ide_package_.presentation.ui.screens.home.components.InfoSection(
            title = "Today's Goals",
            rows = todayGoalRows
        )

        Spacer(modifier = Modifier.height(16.dp))

        _root_ide_package_.presentation.ui.screens.home.components.InfoSection(
            title = "Achievements",
            rows = achievementRows
        )

        Spacer(modifier = Modifier.height(16.dp))

        _root_ide_package_.presentation.ui.screens.home.components.InfoSection(
            title = "Quick Stats",
            rows = quickStatsRows
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (goals.isNotEmpty()) {
            _root_ide_package_.presentation.ui.screens.home.components.GoalList(
                goals = goals,
                onGoalClick = { goalName ->
                    onNavigateToDetails(goalName)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}