package model.di

import android.content.Context
import androidx.room.Room
import com.example.goaltrack.model.repository.GoalRepository
import com.example.goaltrack.model.repository.GoalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import model.data.local.dao.GoalDao
import model.data.local.db.GoalTrackDatabase
import javax.inject.Singleton
import model.data.local.util.MigrationUtil

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGoalRepository(
        impl: GoalRepositoryImpl
    ): GoalRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGoalTrackDatabase(
        @ApplicationContext context: Context
    ): GoalTrackDatabase {
        return Room.databaseBuilder(
            context,
            GoalTrackDatabase::class.java,
            "goaltrack_database"
        )
            .addMigrations(MigrationUtil.MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideGoalDao(
        database: GoalTrackDatabase
    ): GoalDao {
        return database.goalDao()
    }
}