package com.example.workoutplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.workoutplanner.domain.ExerciseBodyType
import com.example.workoutplanner.domain.Template
import com.example.workoutplanner.listeners.AppSpinnerListener
import com.example.workoutplanner.view.BodyPartViewModel
import com.example.workoutplanner.view.TemplateViewModel
import kotlinx.android.synthetic.main.fragment_standard_select_template.*

class StandardSelectTemplateFragment : Fragment() {

    companion object {
        const val newWordActivityRequestCode = 1
    }

    lateinit var templateViewModel: TemplateViewModel
    lateinit var exerciseBodyPartViewModel: BodyPartViewModel

    lateinit var templateSpinnerListener: AppSpinnerListener<Template>
    lateinit var exerciseBodyTypeSpinnerListener: AppSpinnerListener<ExerciseBodyType>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModelProvider = ViewModelProviders.of(this)
        templateViewModel = viewModelProvider.get(TemplateViewModel::class.java)
        exerciseBodyPartViewModel = viewModelProvider.get(BodyPartViewModel::class.java)

        return inflater.inflate(R.layout.fragment_standard_select_template, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bodyPartSpinner: Spinner = view.findViewById(R.id.exerciseBodyPartSpinner)
        val templateSpinner: Spinner = view.findViewById(R.id.templateSpinner)

        view?.let {

            // Init spinners
            templateViewModel.allTemplates.observe(this, Observer { templates ->
                templates?.let { data ->
                    templateSpinnerListener = AppSpinnerListener(data)
                    templateSpinner.onItemSelectedListener = templateSpinnerListener
                    var arrayAdapter = ArrayAdapter<Template>(context, R.layout.spinner_color_layout)
                    arrayAdapter.setDropDownViewResource(R.layout.spiner_dropdown_layout)
                    arrayAdapter.addAll(data.map { template -> template })
                    templateSpinner.adapter = arrayAdapter
                }
            })

            exerciseBodyPartViewModel.allExerciseBodyTypes.observe(this, Observer { exerciseBodyTypes ->
                exerciseBodyTypes?.let { data ->
                    exerciseBodyTypeSpinnerListener = AppSpinnerListener(data)
                    bodyPartSpinner.onItemSelectedListener = exerciseBodyTypeSpinnerListener
                    var arrayAdapter = ArrayAdapter<ExerciseBodyType>(context, R.layout.spinner_color_layout)
                    arrayAdapter.setDropDownViewResource(R.layout.spiner_dropdown_layout)
                    arrayAdapter.addAll(data.map { bodyType -> bodyType })
                    bodyPartSpinner.adapter = arrayAdapter
                }
            })
        }
        btnStandardBasicNext.setOnClickListener {
            var selectedTemplate = templateSpinnerListener.selectedItem
            var selectedBodyType = exerciseBodyTypeSpinnerListener.selectedItem
            var bundle: Bundle = Bundle()

            bundle.putLong("series", selectedTemplate.series)
            bundle.putLong("reps", selectedTemplate.reps)
            bundle.putLong("bodyTypeId", selectedBodyType.id)

            it.findNavController().navigate(R.id.action_to_reps, bundle)

        }
    }
}
