package com.example.workoutplanner.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workoutplanner.db.dao.TemplateDao
import com.example.workoutplanner.domain.Template

@Database(entities = [Template::class], version = 1)
abstract class AbstractDatabase : RoomDatabase() {

    abstract fun templateDao(): TemplateDao

    companion object {
        @Volatile
        private var INSTANCE: AbstractDatabase? = null

        fun getDatabase(context: Context): AbstractDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AbstractDatabase::class.java,
                    "Word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}