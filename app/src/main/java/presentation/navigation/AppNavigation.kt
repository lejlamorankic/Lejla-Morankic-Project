package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import presentation.ui.screens.add.AddGoalScreen
import presentation.ui.screens.achievements.AchievementsScreen
import presentation.ui.screens.details.GoalDetailScreen
import presentation.ui.screens.goals.GoalsScreen
import presentation.ui.screens.home.HomeScreen
import presentation.ui.screens.stats.StatsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToAdd = {
                    navController.navigate("add")
                },
                onNavigateToDetails = { goalName ->
                    navController.navigate("details/$goalName")
                },
                onNavigateToAchievements = {
                    navController.navigate("achievements")
                },
                onNavigateToStats = {
                    navController.navigate("stats")
                },
                onNavigateToGoals = {
                    navController.navigate("goals")
                }
            )
        }

        composable("add") {
            AddGoalScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("goals") {
            GoalsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onGoalClick = { goalName ->
                    navController.navigate("details/$goalName")
                }
            )
        }

        composable(
            route = "details/{goalName}",
            arguments = listOf(
                navArgument("goalName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val goalName = backStackEntry.arguments?.getString("goalName") ?: ""

            GoalDetailScreen(
                goalName = goalName,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("achievements") {
            AchievementsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("stats") {
            StatsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}