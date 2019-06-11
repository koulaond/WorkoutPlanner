package com.example.workoutplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

class StandardSelectTemplateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val templateSpinner: Spinner = view!!.findViewById(R.id.templateSpinner)

        ArrayAdapter.createFromResource(
            this.context,
            R.array.exerciseTemplates,
            android.R.layout.simple_spinner_item

        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            templateSpinner.adapter = adapter
        }
        return inflater.inflate(R.layout.fragment_standard_select_template, container, false)
    }

}
