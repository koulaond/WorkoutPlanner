package com.example.workoutplanner.db.dao

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.workoutplanner.domain.Template

@Dao
interface TemplateDao : AbstractDao<Template> {

    @Query("select * from template")
    fun getAllTemplates(): LiveData<Array<Template>>

}