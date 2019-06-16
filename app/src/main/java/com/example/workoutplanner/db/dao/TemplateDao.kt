package com.example.workoutplanner.db.dao

import android.os.AsyncTask
import androidx.room.Dao
import androidx.room.Query
import com.example.workoutplanner.domain.Template

@Dao
interface TemplateDao : AbstractDao<Template> {

    @Query("select * from template")
    fun getAllTemplates(): Array<Template>

    class TemplateGetAsyncTask(var dao: TemplateDao) : AsyncTask<Void, Void, Array<Template>>() {
        override fun doInBackground(vararg params: Void?): Array<Template> {
            return dao.getAllTemplates()
        }


    }
}