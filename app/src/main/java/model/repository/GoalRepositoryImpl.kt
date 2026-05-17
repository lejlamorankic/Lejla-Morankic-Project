package com.example.goaltrack.model.repository

import com.example.goaltrack.model.Goal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import model.data.local.dao.GoalDao
import model.data.local.entity.GoalEntity
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(
    private val goalDao: GoalDao
) : GoalRepository {

    override fun getGoals(): Flow<List<Goal>> {
        return goalDao.getAllGoals().map { entities ->
            entities.map { entity ->
                Goal(
                    name = entity.name
                )
            }
        }
    }

    override suspend fun addGoal(goal: Goal) {
        goalDao.insertGoal(
            GoalEntity(
                name = goal.name,
                status = "Active",
                category = "General",
                xp = 20
            )
        )
    }

    override suspend fun deleteGoal(goal: Goal) {
        val entity = goalDao.getGoalByName(goal.name)

        if (entity != null) {
            goalDao.deleteGoal(entity)
        }
    }

    override suspend fun updateGoal(oldGoal: Goal, newGoal: Goal) {
        val oldEntity = goalDao.getGoalByName(oldGoal.name)

        if (oldEntity != null) {
            goalDao.updateGoal(
                oldEntity.copy(
                    name = newGoal.name
                )
            )
        }
    }
}