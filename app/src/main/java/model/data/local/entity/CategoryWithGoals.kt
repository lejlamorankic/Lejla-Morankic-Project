package model.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithGoals(
    @Embedded
    val category: CategoryEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val goals: List<GoalEntity>
)