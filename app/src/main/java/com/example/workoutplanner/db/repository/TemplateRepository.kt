package com.example.workoutplanner.db.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.workoutplanner.db.dao.TemplateDao
import com.example.workoutplanner.domain.Template

class TemplateRepository(private val templateDao: TemplateDao) {

    val allTemplates: LiveData<Array<Template>> = templateDao.getAllTemplates()

    @WorkerThread
    fun insert(item: Template) {
        templateDao.insert(item)
    }

    @WorkerThread
    fun update(item: Template) {
        templateDao.update(item)
    }

    @WorkerThread
    fun delete(item: Template) {
        templateDao.delete(item)
    }
}