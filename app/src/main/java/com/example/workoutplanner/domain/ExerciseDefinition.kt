package com.example.workoutplanner.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_definition")
data class ExerciseDefinition(
    @PrimaryKey var id: Long?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "series") var series: Map<Int, Int>,
    @ColumnInfo(name = "body_type_id") var bodyTypeId: Long
)