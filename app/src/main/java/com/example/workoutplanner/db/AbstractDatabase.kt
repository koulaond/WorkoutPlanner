package com.example.workoutplanner.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.workoutplanner.db.dao.TemplateDao
import com.example.workoutplanner.domain.Template
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Template::class], version = 1)
abstract class AbstractDatabase : RoomDatabase() {

    abstract fun templateDao(): TemplateDao

    companion object {
        @Volatile
        private var INSTANCE: AbstractDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AbstractDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AbstractDatabase::class.java,
                    "Word_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(TemplateDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class TemplateDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.templateDao())
                }
            }
        }

        fun populateDatabase(templateDao: TemplateDao) {
            templateDao.deleteAll()

            var template = Template(1, 2, 3)
            templateDao.insert(template)

            template = Template(2, 2, 4)
            templateDao.insert(template)

            template = Template(3, 2, 5)
            templateDao.insert(template)

        }
    }
}