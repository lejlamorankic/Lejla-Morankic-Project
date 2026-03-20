package com.example.goaltrack.viewmodel

import androidx.lifecycle.ViewModel
import com.example.goaltrack.model.Goal

class GoalViewModel : ViewModel() {

    private val goals = mutableListOf<Goal>()

    fun addGoal(goal: Goal) {
        goals.add(goal)
    }

    fun getGoals(): List<Goal> {
        return goals
    }
}