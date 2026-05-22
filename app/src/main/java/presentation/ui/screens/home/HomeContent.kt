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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goaltrack.model.Goal
import presentation.ui.screens.home.components.CategoryChip
import presentation.ui.screens.home.components.DashboardTitle
import presentation.ui.screens.home.components.GoalInputSection
import presentation.ui.screens.home.components.GoalList
import presentation.ui.screens.home.components.InfoRowData
import presentation.ui.screens.home.components.InfoSection
import presentation.ui.screens.home.components.UserProgressCard
import presentation.viewmodel.HomeUiState

@Composable
fun HomeContent(
    uiState: HomeUiState,
    categories: List<String>,
    achievements: List<String>,
    filteredGoals: List<Goal>,
    onGoalTextChange: (String) -> Unit,
    onAddGoalClick: () -> Unit,
    onCategorySelected: (String) -> Unit,
    onNavigateToAdd: () -> Unit,
    onNavigateToAchievements: () -> Unit,
    onNavigateToGoals: () -> Unit,
    onNavigateToStats: () -> Unit,
    onNavigateToDetails: (String) -> Unit
) {
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
                    isSelected = uiState.selectedCategory == category,
                    onClick = {
                        onCategorySelected(category)
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
            onGoalTextChange = onGoalTextChange,
            onAddClick = onAddGoalClick,
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
                onGoalClick = onNavigateToDetails
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}