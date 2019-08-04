package com.example.workoutplanner.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.workoutplanner.domain.ExerciseBodyType
import com.example.workoutplanner.domain.ExerciseBodyTypeAndAllExerciseDefinitions

@Dao
interface ExerciseBodyTypeDao : AbstractDao<ExerciseBodyType> {

    @Transaction
    @Query("select * from exercise_body_type")
    fun getAllBodyParts(): LiveData<Array<ExerciseBodyTypeAndAllExerciseDefinitions>>

    @Query(value = "select count(*) from exercise_body_type")
    fun count(): Int

    @Query("DELETE FROM exercise_body_type")
    fun deleteAll()
}