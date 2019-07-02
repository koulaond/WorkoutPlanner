package com.example.workoutplanner.db.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.workoutplanner.db.dao.ExerciseBodyTypeDao
import com.example.workoutplanner.domain.ExerciseBodyType

class ExerciseBodyPartRepository(private val eexeciseBodyPartDao: ExerciseBodyTypeDao) {

    val allExerciseBodyParts: LiveData<Array<ExerciseBodyType>> = eexeciseBodyPartDao.getAllBodyParts()

    @WorkerThread
    fun insert(item: ExerciseBodyType) {
        eexeciseBodyPartDao.insert(item)
    }

    @WorkerThread
    fun update(item: ExerciseBodyType) {
        eexeciseBodyPartDao.update(item)
    }

    @WorkerThread
    fun delete(item: ExerciseBodyType) {
        eexeciseBodyPartDao.delete(item)
    }
}