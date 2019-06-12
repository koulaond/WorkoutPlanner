package com.example.workoutplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class StandardSelectTemplateFragment : Fragment(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_standard_select_template, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view?.let {
            val templateSpinner: Spinner = it.findViewById(R.id.templateSpinner)
            templateSpinner.onItemSelectedListener = this
            ArrayAdapter.createFromResource(
                this.context!!,
                R.array.exerciseTemplates,
                R.layout.spinner_color_layout

            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spiner_dropdown_layout)
                templateSpinner.adapter = adapter
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}
