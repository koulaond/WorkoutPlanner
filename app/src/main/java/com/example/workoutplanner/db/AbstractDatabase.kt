package com.example.workoutplanner.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.workoutplanner.db.dao.ExerciseBodyTypeDao
import com.example.workoutplanner.db.dao.ExerciseDefinitionDao
import com.example.workoutplanner.db.dao.TemplateDao
import com.example.workoutplanner.domain.ExerciseBodyType
import com.example.workoutplanner.domain.ExerciseDefinition
import com.example.workoutplanner.domain.Template
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Template::class, ExerciseBodyType::class, ExerciseDefinition::class], version = 1)
@TypeConverters(DatabaseTypeConverters::class)
abstract class AbstractDatabase : RoomDatabase() {

    abstract fun templateDao(): TemplateDao

    abstract fun exerciseBodyTypeDao(): ExerciseBodyTypeDao

    abstract fun exerciseDefinitionDao() : ExerciseDefinitionDao

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
                    "workout_database"
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
                    populateDatabase(database.templateDao(), database.exerciseBodyTypeDao())
                }
            }
        }

        fun populateDatabase(templateDao: TemplateDao, exerciseBodyTypeDao: ExerciseBodyTypeDao) {


            if (templateDao.count() == 0) {
                arrayOf(
                    Template(1, 2, 3),
                    Template(2, 2, 4),
                    Template(3, 2, 5),
                    Template(4, 2, 6),
                    Template(5, 3, 3),
                    Template(6, 3, 4),
                    Template(7, 3, 5),
                    Template(8, 3, 6),
                    Template(9, 4, 3),
                    Template(10, 4, 4),
                    Template(11, 4, 5),
                    Template(12, 4, 6)
                ).forEach { template -> templateDao.insert(template) }
            }

            if (exerciseBodyTypeDao.count() == 0) {
                arrayOf(
                    ExerciseBodyType(
                        1,
                        "Benchpress",
                        "One of the fundamental exercises focused on chest, triceps and shoulders",
                        arrayListOf("CHEST", "TRICEPS", "SHOULDERS")
                    ),
                    ExerciseBodyType(
                        2,
                        "Squats",
                        "One of the fundamental exercises focused on legs",
                        arrayListOf("LEGS")
                    ),
                    ExerciseBodyType(
                        3,
                        "Deadlift",
                        "One of the fundamental exercises that covers 80% of body muscle",
                        arrayListOf("BACK", "LEGS", "SHOULDERS", "CORE", "CHEST")
                    ),
                    ExerciseBodyType(
                        4,
                        "Clean and jerk",
                        "This exercises covers mainly shoulders, then legs and lower back",
                        arrayListOf("SHOULDERS", "LEGS", "BACK")
                    ),
                    ExerciseBodyType(
                        5,
                        "Pullover",
                        "Very powerful core exercise",
                        arrayListOf("CHEST", "BACK", "TRICEPS")
                    ),
                    ExerciseBodyType(
                        6,
                        "Pullups",
                        "Basic exercise for mastering back and core deep muscles",
                        arrayListOf("BACK", "CORE", "BICEPS")
                    ),
                    ExerciseBodyType(
                        7,
                        "Yates rows",
                        "Back and rear shoulders exercises",
                        arrayListOf("BACK", "SHOULDERS")
                    )
                ).forEach { bodyType -> exerciseBodyTypeDao.insert(bodyType) }
            }
        }
    }
}