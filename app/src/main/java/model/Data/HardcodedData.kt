package com.example.goaltrack.model.data

import com.example.goaltrack.model.Goal

object HardcodedData {

    val sampleGoals = listOf(
        Goal("Study Kotlin"),
        Goal("Workout"),
        Goal("Drink Water"),
        Goal("Read 10 pages"),
        Goal("Finish assignment")
    )

    val sampleAchievements = listOf(
        "First Goal Added",
        "3 Goals Completed",
        "Consistent Starter",
        "Week 1 Active"
    )

    val sampleCategories = listOf(
        "Study",
        "Health",
        "Fitness",
        "Productivity",
        "Personal"
    )

    val sampleStats = listOf(
        "Total Goals" to "5",
        "Current XP" to "100",
        "Achievements" to "4"
    )
}