package model.data.remote.dto

data class GoalDto(
    val userId: Int = 1,
    val id: Int? = null,
    val title: String,
    val body: String
)