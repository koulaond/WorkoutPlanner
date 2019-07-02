package com.example.workoutplanner.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.workoutplanner.domain.ExerciseBodyType

@Dao
interface ExerciseBodyTypeDao : AbstractDao<ExerciseBodyType> {

    @Query("select * from exercise_body_type")
    fun getAllBodyParts(): LiveData<Array<ExerciseBodyType>>

    @Query(value = "select count(*) from exercise_body_type")
    fun count(): Int

    @Query("DELETE FROM exercise_body_type")
    fun deleteAll()
}