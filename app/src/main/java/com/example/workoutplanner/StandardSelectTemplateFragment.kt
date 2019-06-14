package com.example.workoutplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.workoutplanner.listeners.AppSpinnerListener
import kotlinx.android.synthetic.main.fragment_standard_select_template.*

class StandardSelectTemplateFragment : Fragment() {

    lateinit var bodyPartsSpinnerListener: AppSpinnerListener
    lateinit var templateSpinnerListener: AppSpinnerListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val templates = resources.getStringArray(R.array.exerciseTemplates)
        val bodyParts = resources.getStringArray(R.array.exerciseBodyParts)

        this.templateSpinnerListener = AppSpinnerListener(templates, templates[0])
        this.bodyPartsSpinnerListener = AppSpinnerListener(bodyParts, bodyParts[0])
        return inflater.inflate(R.layout.fragment_standard_select_template, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnStandardBasicNext.setOnClickListener {
            if (templateSpinnerListener.selectedItem.equals(templateSpinnerListener.emptyValue)) {
                Toast.makeText(context, "Select template", Toast.LENGTH_SHORT).show()

            } else if (bodyPartsSpinnerListener.selectedItem.equals(bodyPartsSpinnerListener.emptyValue)) {
                Toast.makeText(context, "Select body part", Toast.LENGTH_SHORT).show()

            } else {
                it.findNavController().navigate(R.id.action_to_reps)
            }
        }
        view?.let {
            val templateSpinner: Spinner = it.findViewById(R.id.templateSpinner)
            val bodyPartSpinner: Spinner = it.findViewById(R.id.exerciseBodyPartSpinner)

            templateSpinner.onItemSelectedListener = templateSpinnerListener
            bodyPartSpinner.onItemSelectedListener = bodyPartsSpinnerListener

            // Init spinners
            initAdapterForSpinner(R.array.exerciseTemplates, templateSpinner)
            initAdapterForSpinner(R.array.exerciseBodyParts, bodyPartSpinner)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapterForSpinner(arrayResource: Int, spinner: Spinner) {
        ArrayAdapter.createFromResource(
            this.context!!,
            arrayResource,
            R.layout.spinner_color_layout

        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spiner_dropdown_layout)
            spinner.adapter = adapter
        }
    }
}
