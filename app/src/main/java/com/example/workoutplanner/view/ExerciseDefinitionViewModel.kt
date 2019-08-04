package com.example.workoutplanner.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutplanner.db.AbstractDatabase
import com.example.workoutplanner.db.dao.ExerciseDefinitionDao
import com.example.workoutplanner.db.repository.ExerciseDefinitionRepository
import com.example.workoutplanner.domain.ExerciseDefinition

class ExerciseDefinitionViewModel(application: Application) : AndroidViewModel(application) {

    private val exerciseDefinitionRepository: ExerciseDefinitionRepository
    val allExerciseDefinitions: LiveData<Array<ExerciseDefinition>>

    init {
        val exerciseDefinitionDao: ExerciseDefinitionDao = AbstractDatabase.getDatabase(application, viewModelScope).exerciseDefinitionDao()
        this.exerciseDefinitionRepository = ExerciseDefinitionRepository(exerciseDefinitionDao)
        this.allExerciseDefinitions = exerciseDefinitionRepository.allExerciseDefinitions
    }

    fun insert(exerciseDefinition: ExerciseDefinition)  {
        exerciseDefinitionRepository.insert(exerciseDefinition)
    }
}