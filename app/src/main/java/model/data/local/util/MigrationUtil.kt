package model.data.local.util

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationUtil {

    val MIGRATION_1_2 = object : Migration(1, 2) {

        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL(
                """
                ALTER TABLE goals
                ADD COLUMN categoryId INTEGER NOT NULL DEFAULT 1
                """.trimIndent()
            )
        }
    }
}