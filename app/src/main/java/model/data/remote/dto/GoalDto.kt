package model.data.remote.dto

data class GoalDto(
    val id: Int? = null,
    val name: String,
    val status: String,
    val category: String,
    val xp: Int
)