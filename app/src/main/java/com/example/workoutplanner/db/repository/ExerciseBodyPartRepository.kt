package com.example.workoutplanner.db.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.workoutplanner.db.dao.ExerciseBodyTypeDao
import com.example.workoutplanner.domain.ExerciseBodyType
import com.example.workoutplanner.domain.ExerciseBodyTypeAndAllExerciseDefinitions

class ExerciseBodyPartRepository(private val execiseBodyPartDao: ExerciseBodyTypeDao) {

    val allExerciseBodyParts: LiveData<Array<ExerciseBodyTypeAndAllExerciseDefinitions>> = execiseBodyPartDao.getAllBodyParts()

    @WorkerThread
    fun insert(item: ExerciseBodyType) {
        execiseBodyPartDao.insert(item)
    }

    @WorkerThread
    fun update(item: ExerciseBodyType) {
        execiseBodyPartDao.update(item)
    }

    @WorkerThread
    fun delete(item: ExerciseBodyType) {
        execiseBodyPartDao.delete(item)
    }
}