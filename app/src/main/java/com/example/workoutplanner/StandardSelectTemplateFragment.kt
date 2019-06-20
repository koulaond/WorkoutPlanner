package com.example.workoutplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.workoutplanner.listeners.AppSpinnerListener
import com.example.workoutplanner.view.TemplateViewModel
import kotlinx.android.synthetic.main.fragment_standard_select_template.*
import java.util.function.Consumer

class StandardSelectTemplateFragment : Fragment() {

    companion object {
        const val newWordActivityRequestCode = 1
    }

    lateinit var bodyPartsSpinnerListener: AppSpinnerListener
    lateinit var templateSpinnerListener: AppSpinnerListener

    lateinit var templateViewModel: TemplateViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        templateViewModel = ViewModelProviders.of(this).get(TemplateViewModel::class.java)

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
            templateViewModel.allTemplates.value?.map { template -> "${template.series}x${template.reps}" }
                ?.let { data -> initAdapterForSpinner(R.array.exerciseTemplates, templateSpinner, data) }
        }
        //initAdapterForSpinner(R.array.exerciseBodyParts, bodyPartSpinner)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapterForSpinner(arrayResource: Int, spinner: Spinner, data: List<String>) {
        ArrayAdapter.createFromResource(
            this.context!!,
            arrayResource,
            R.layout.spinner_color_layout

        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spiner_dropdown_layout)
            adapter.addAll(data)
            spinner.adapter = adapter
        }
    }
}
