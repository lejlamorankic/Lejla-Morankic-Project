package model.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import model.data.local.entity.StatsEntity

@Dao
interface StatsDao {

    @Query("SELECT * FROM stats")
    fun getAllStats(): Flow<List<StatsEntity>>

    @Insert
    suspend fun insertStats(stats: StatsEntity)

    @Update
    suspend fun updateStats(stats: StatsEntity)

    @Delete
    suspend fun deleteStats(stats: StatsEntity)
}