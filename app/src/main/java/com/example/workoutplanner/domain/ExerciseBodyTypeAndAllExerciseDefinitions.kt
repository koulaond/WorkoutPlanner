package com.example.workoutplanner.domain

import androidx.room.Embedded
import androidx.room.Relation

data class ExerciseBodyTypeAndAllExerciseDefinitions (
    @Embedded var exerciseBodyType: ExerciseBodyType,
    @Relation(parentColumn = "id", entityColumn = "body_type_id") var exerciseDefinitions: List<ExerciseDefinition>
)