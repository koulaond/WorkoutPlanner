package com.example.workoutplanner

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import android.widget.LinearLayout.LayoutParams
import androidx.lifecycle.ViewModelProviders
import com.example.workoutplanner.db.AbstractDatabase
import com.example.workoutplanner.db.dao.ExerciseDefinitionDao
import com.example.workoutplanner.db.dao.async.InsertAsyncTask
import com.example.workoutplanner.domain.ExerciseBodyType
import com.example.workoutplanner.domain.ExerciseDefinition
import com.example.workoutplanner.domain.Template
import com.example.workoutplanner.filters.EditTextNumberRestrictionFilter
import com.example.workoutplanner.utils.ViewFactory.Companion.editText
import com.example.workoutplanner.utils.ViewFactory.Companion.textView
import com.example.workoutplanner.view.ExerciseDefinitionViewModel

class StandardRepsFragment : Fragment() {

    companion object {
        private const val TEMPLATE = "template"
        private const val BODY_TYPE = "bodyType"
        private const val INPUT_TEXT_SIZE = 24f
        private const val INPUT_SERIES_LABEL_WIDTH = 150
        private const val INPUT_REPS_LABEL_WIDTH = 175
        private const val INPUT_LABEL_HEIGHT = 90
        private const val NUMBER_MIN = 1
        private const val NUMBER_SERIES_MAX = 30
        private const val NUMBER_REPS_MAX = 99
    }

    private lateinit var exerciseName: String
    private lateinit var bodyType: ExerciseBodyType
    private var reps: Int = 0
    private var series: Int = 0

    private var rows: MutableList<LinearLayout> = ArrayList()
    private lateinit var exerciseDefinitionViewModel: ExerciseDefinitionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModelProvider = ViewModelProviders.of(this)
        exerciseDefinitionViewModel = viewModelProvider.get(ExerciseDefinitionViewModel::class.java)
        arguments?.let {
            exerciseName = it.getString("exerciseName") as String
            bodyType = it.getSerializable(BODY_TYPE) as ExerciseBodyType
            var template = it.getSerializable(TEMPLATE) as Template
            reps = template.reps
            series = template.series
        }

        val view: View = inflater.inflate(R.layout.fragment_standard_reps, container, false)
        view.let {
            it.findViewById<EditText>(R.id.series_reps_series_input)?.let {
                it.setText(series.toString())
                it.filters = arrayOf(EditTextNumberRestrictionFilter(NUMBER_MIN, NUMBER_SERIES_MAX))

                it.addTextChangedListener(object : TextWatcher {

                    override fun afterTextChanged(editable: Editable?) {
                        val seriesInputValue = it.text.toString()

                        if (seriesInputValue.isNotBlank()) {
                            series = seriesInputValue.toInt()
                        } else {
                            series = NUMBER_MIN
                        }
                        view?.let {
                            updateContainer(it)
                        }
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                })
            }
            it.findViewById<EditText>(R.id.series_reps_reps_input)?.let {
                it.setText(reps.toString())
                it.filters = arrayOf(EditTextNumberRestrictionFilter(NUMBER_MIN, NUMBER_REPS_MAX))

                it.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        val repsInputValue = it.text.toString()

                        if (repsInputValue.isNotBlank()) {
                            reps = repsInputValue.toInt()
                            updateValuesInRepsFields()
                        } else {
                            reps = NUMBER_MIN
                        }
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                })
            }
            it.findViewById<Button>(R.id.btnStandardBasicFinish)?.let { it -> it.setOnClickListener { finish() } }
            updateContainer(view)
        }
        return view
    }

    private fun finish() {
        var seriesMap: MutableMap<Int, Int> = HashMap()
        rows.forEachIndexed { index, row ->
            run {
                val input = row.findViewWithTag("REP_INPUT") as EditText
                val value = input.text.toString()
                seriesMap.put(index + 1, value.toInt())
            }
        }
        val exerciseDefinition = ExerciseDefinition(null, exerciseName, seriesMap, bodyType.id)
        exerciseDefinitionViewModel.insert(exerciseDefinition)
    }

    private fun updateContainer(view: View) {
        if (series > 0) {
            context?.let {
                createMissingRows(it)
                var layout = LinearLayout(context)

                layout.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                layout.orientation = LinearLayout.VERTICAL

                for (index in 0 until series) {
                    val row = rows.get(index)

                    row.parent?.let {
                        val parent = it as ViewGroup
                        parent.removeView(row)
                    }
                    layout.addView(row)
                }
                val container = view.findViewById<ScrollView>(R.id.series_reps_container)
                container.removeAllViews()
                container.addView(layout)
            }
        }
    }

    private fun updateValuesInRepsFields() {
        rows.forEach { row ->
            val repField = row.findViewWithTag<EditText>("REP_INPUT")
            repField.setText(reps.toString())
        }
    }

    private fun createMissingRows(context: Context) {
        if (rows.size < series) {
            for (index in rows.size..series - NUMBER_MIN) {
                val rowLayout = LinearLayout(context)

                rowLayout.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                rowLayout.orientation = LinearLayout.HORIZONTAL

                val label: TextView = textView(
                    "Series ${index + NUMBER_MIN}",
                    INPUT_TEXT_SIZE, resources.getColor(R.color.inputFieldWhite), INPUT_SERIES_LABEL_WIDTH,
                    INPUT_LABEL_HEIGHT, context
                )
                val repsInput = editText(
                    INPUT_TEXT_SIZE,
                    getResources().getColor(R.color.inputFieldWhite),
                    INPUT_REPS_LABEL_WIDTH,
                    INPUT_LABEL_HEIGHT,
                    InputType.TYPE_CLASS_NUMBER,
                    context
                )
                repsInput.setText(reps.toString())
                repsInput.filters = arrayOf(EditTextNumberRestrictionFilter(NUMBER_MIN, 99))

                rowLayout.addView(label)
                rowLayout.addView(repsInput)
                rows.add(rowLayout)
            }
        }
    }
}
