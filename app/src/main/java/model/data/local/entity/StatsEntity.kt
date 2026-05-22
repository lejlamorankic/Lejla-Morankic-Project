package model.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats")
data class StatsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val totalGoals: Int,

    val currentXP: Int
)