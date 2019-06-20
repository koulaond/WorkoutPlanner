package com.example.workoutplanner.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.workoutplanner.domain.Template

@Dao
interface TemplateDao : AbstractDao<Template> {

    @Query("select * from template")
    fun getAllTemplates(): LiveData<Array<Template>>

    @Query("DELETE FROM template")
    fun deleteAll()
}