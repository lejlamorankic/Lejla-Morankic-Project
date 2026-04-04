package ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ui.screens.add.AddGoalScreen
import ui.screens.achievements.AchievementsScreen
import ui.screens.details.GoalDetailScreen
import ui.screens.goals.GoalsScreen
import ui.screens.home.HomeScreen
import ui.screens.stats.StatsScreen

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