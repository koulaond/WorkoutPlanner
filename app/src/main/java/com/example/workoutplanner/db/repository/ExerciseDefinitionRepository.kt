package com.example.workoutplanner.db.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.workoutplanner.db.dao.ExerciseDefinitionDao
import com.example.workoutplanner.db.dao.async.InsertAsyncTask
import com.example.workoutplanner.domain.ExerciseDefinition

class ExerciseDefinitionRepository(private val exerciseDefinitionDao: ExerciseDefinitionDao) {

    val allExerciseDefinitions: LiveData<Array<ExerciseDefinition>> = exerciseDefinitionDao.getAllExerciseDefinitions()
    val insertAsyncTask: InsertAsyncTask<ExerciseDefinition, ExerciseDefinitionDao> = InsertAsyncTask(exerciseDefinitionDao)

    @WorkerThread
    fun insert(item: ExerciseDefinition) {
        insertAsyncTask.execute()
    }

    @WorkerThread
    fun update(item: ExerciseDefinition) {
        exerciseDefinitionDao.update(item)
    }

    @WorkerThread
    fun delete(item: ExerciseDefinition) {
        exerciseDefinitionDao.delete(item)
    }
}