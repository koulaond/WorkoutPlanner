package com.example.workoutplanner.db.dao

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.workoutplanner.domain.ExerciseBodyType
import com.example.workoutplanner.domain.ExerciseDefinition

@Dao
interface ExerciseDefinitionDao : AbstractDao<ExerciseDefinition> {

    @Query("select * from exercise_definition")
    fun getAllExerciseDefinitions(): LiveData<Array<ExerciseDefinition>>

    @Query(value = "select count(*) from exercise_definition")
    fun count(): Int

    @Query("DELETE FROM exercise_definition")
    fun deleteAll()
}