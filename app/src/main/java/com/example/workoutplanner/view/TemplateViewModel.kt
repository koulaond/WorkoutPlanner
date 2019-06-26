package com.example.workoutplanner.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import com.example.workoutplanner.db.AbstractDatabase
import com.example.workoutplanner.db.dao.TemplateDao
import com.example.workoutplanner.db.repository.TemplateRepository
import com.example.workoutplanner.domain.Template

class TemplateViewModel(application: Application) : AndroidViewModel(application) {

    private val templateRepository: TemplateRepository
    val allTemplates: LiveData<Array<Template>>

    init {
        val templateDao: TemplateDao = AbstractDatabase.getDatabase(application, viewModelScope).templateDao()
        this.templateRepository = TemplateRepository(templateDao)
        allTemplates = templateRepository.allTemplates
    }

    fun insert(template: Template)  {
        templateRepository.insert(template)
    }
}