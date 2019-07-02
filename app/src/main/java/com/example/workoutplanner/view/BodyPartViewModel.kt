package com.example.workoutplanner.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutplanner.db.AbstractDatabase
import com.example.workoutplanner.db.dao.ExerciseBodyTypeDao
import com.example.workoutplanner.db.repository.ExerciseBodyPartRepository
import com.example.workoutplanner.domain.ExerciseBodyType

class BodyPartViewModel(application: Application) : AndroidViewModel(application)  {
    private val exerciseBodyPartRepository: ExerciseBodyPartRepository

    val allExerciseBodyTypes: LiveData<Array<ExerciseBodyType>>

    init {
        val exerciseBodyTypeDao: ExerciseBodyTypeDao = AbstractDatabase.getDatabase(application, viewModelScope).exerciseBodyTypeDao()
        this.exerciseBodyPartRepository = ExerciseBodyPartRepository(exerciseBodyTypeDao)
        this.allExerciseBodyTypes = exerciseBodyPartRepository.allExerciseBodyParts
    }

    fun insert(exerciseBodyType: ExerciseBodyType)  {
        exerciseBodyPartRepository.insert(exerciseBodyType)
    }
}