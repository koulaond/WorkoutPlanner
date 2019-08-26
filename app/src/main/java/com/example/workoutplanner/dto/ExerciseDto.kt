package com.example.workoutplanner.dto

data class ExerciseDto(
    var id: Long?,
    var name: String,
    var seriesAndReps: Map<Int, Int>,
    var bodyType: String
)