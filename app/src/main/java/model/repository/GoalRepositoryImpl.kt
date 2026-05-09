package com.example.goaltrack.model.repository

import com.example.goaltrack.model.Goal
import com.example.goaltrack.model.data.HardcodedData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class GoalRepositoryImpl : GoalRepository {

    private val goalsFlow = MutableStateFlow(HardcodedData.sampleGoals)

    override fun getGoals(): Flow<List<Goal>> {
        return goalsFlow
    }

    override suspend fun addGoal(goal: Goal) {
        delay(300)
        goalsFlow.value = goalsFlow.value + goal
    }

    override suspend fun deleteGoal(goal: Goal) {
        delay(300)
        goalsFlow.value = goalsFlow.value - goal
    }

    override suspend fun updateGoal(oldGoal: Goal, newGoal: Goal) {
        delay(300)
        goalsFlow.value = goalsFlow.value.map {
            if (it == oldGoal) newGoal else it
        }
    }
}