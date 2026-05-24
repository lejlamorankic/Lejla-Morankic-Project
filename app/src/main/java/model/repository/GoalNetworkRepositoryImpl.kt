package com.example.goaltrack.model.repository

import model.data.remote.api.GoalApiService
import model.data.remote.dto.GoalDto
import javax.inject.Inject

class GoalNetworkRepositoryImpl @Inject constructor(
    private val api: GoalApiService
) : GoalNetworkRepository {

    override suspend fun getGoals(): List<GoalDto> {
        return api.getGoals()
    }

    override suspend fun createGoal(goal: GoalDto): GoalDto {
        return api.createGoal(goal)
    }

    override suspend fun updateGoal(
        id: Int,
        goal: GoalDto
    ): GoalDto {
        return api.updateGoal(id, goal)
    }

    override suspend fun deleteGoal(id: Int) {
        api.deleteGoal(id)
    }
}