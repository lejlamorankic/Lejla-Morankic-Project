package com.example.goaltrack.model.repository

import model.data.remote.dto.GoalDto

interface GoalNetworkRepository {

    suspend fun getGoals(): List<GoalDto>

    suspend fun createGoal(goal: GoalDto): GoalDto

    suspend fun updateGoal(
        id: Int,
        goal: GoalDto
    ): GoalDto

    suspend fun deleteGoal(id: Int)
}