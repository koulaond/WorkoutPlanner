package com.example.workoutplanner.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface AbstractDao<T> {

    @Insert
    fun insert(vararg items: T)

    @Update
    fun update(vararg items: T)

    @Delete
    fun delete(item:T)

}