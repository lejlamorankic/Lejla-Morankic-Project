package presentation.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goaltrack.model.data.HardcodedData
import presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onNavigateToAdd: () -> Unit,
    onNavigateToDetails: (String) -> Unit,
    onNavigateToAchievements: () -> Unit,
    onNavigateToStats: () -> Unit,
    onNavigateToGoals: () -> Unit
) {
    val viewModel: HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val categories = listOf("All") + HardcodedData.sampleCategories
    val achievements = HardcodedData.sampleAchievements

    val filteredGoals = uiState.goals.filter { goal ->
        when (uiState.selectedCategory) {
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

    HomeContent(
        uiState = uiState,
        categories = categories,
        achievements = achievements,
        filteredGoals = filteredGoals,
        onGoalTextChange = viewModel::onGoalTextChange,
        onAddGoalClick = viewModel::addGoal,
        onCategorySelected = viewModel::onCategorySelected,
        onNavigateToAdd = onNavigateToAdd,
        onNavigateToAchievements = onNavigateToAchievements,
        onNavigateToGoals = onNavigateToGoals,
        onNavigateToStats = onNavigateToStats,
        onNavigateToDetails = onNavigateToDetails
    )
}