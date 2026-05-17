package model.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import model.data.local.dao.AchievementDao
import model.data.local.dao.CategoryDao
import model.data.local.dao.GoalDao
import model.data.local.dao.StatsDao
import model.data.local.dao.UserDao
import model.data.local.entity.AchievementEntity
import model.data.local.entity.CategoryEntity
import model.data.local.entity.GoalEntity
import model.data.local.entity.StatsEntity
import model.data.local.entity.UserEntity

@Database(
    entities = [
        GoalEntity::class,
        AchievementEntity::class,
        CategoryEntity::class,
        StatsEntity::class,
        UserEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class GoalTrackDatabase : RoomDatabase() {

    abstract fun goalDao(): GoalDao

    abstract fun achievementDao(): AchievementDao

    abstract fun categoryDao(): CategoryDao

    abstract fun statsDao(): StatsDao

    abstract fun userDao(): UserDao
}