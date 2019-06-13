package com.example.workoutplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_standard_select_template.*

class StandardSelectTemplateFragment : Fragment(), AdapterView.OnItemSelectedListener {

    val selectTemplateInit: String = "SELECT TEMPLATE"
    val selectBodyPartInit: String = "SELECT BODY PART"

    lateinit var templates: Array<String>
    lateinit var bodyParts: Array<String>
    var selectedTemplate: String = selectTemplateInit
    var selectedBodyPart: String = selectBodyPartInit


    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        templates = resources.getStringArray(R.array.exerciseTemplates)
        bodyParts = resources.getStringArray(R.array.exerciseBodyParts)
        return inflater.inflate(R.layout.fragment_standard_select_template, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnStandardBasicNext.setOnClickListener {
            if (!(selectedBodyPart.equals(selectBodyPartInit) || selectedTemplate.equals(selectTemplateInit))) {
                it.findNavController().navigate(R.id.action_to_reps)
            }
        }
        view?.let {
            val templateSpinner: Spinner = it.findViewById(R.id.templateSpinner)
            val bodyPartSpinner: Spinner = it.findViewById(R.id.exerciseBodyPartSpinner)

            templateSpinner.onItemSelectedListener = this
            bodyPartSpinner.onItemSelectedListener = this

            // Template spinner init

            ArrayAdapter.createFromResource(
                this.context!!,
                R.array.exerciseTemplates,
                R.layout.spinner_color_layout

            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spiner_dropdown_layout)
                templateSpinner.adapter = adapter
            }

            // Body parts spinner init
            ArrayAdapter.createFromResource(
                this.context!!,
                R.array.exerciseBodyParts,
                R.layout.spinner_color_layout

            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spiner_dropdown_layout)
                bodyPartSpinner.adapter = adapter
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }


}
