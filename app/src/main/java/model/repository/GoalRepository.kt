package com.example.goaltrack.model.repository

import com.example.goaltrack.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {

    fun getGoals(): Flow<List<Goal>>

    suspend fun addGoal(goal: Goal)

    suspend fun deleteGoal(goal: Goal)

    suspend fun updateGoal(oldGoal: Goal, newGoal: Goal)
}