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
import com.example.workoutplanner.filters.EditTextNumberRestrictionFilter
import com.example.workoutplanner.utils.ViewFactory.Companion.editText
import com.example.workoutplanner.utils.ViewFactory.Companion.textView

class StandardRepsFragment : Fragment() {

    companion object {
        private const val REPS = "reps"
        private const val SERIES = "series"
        private const val BODY_TYPE_ID = "bodyTypeId"
        private const val INPUT_TEXT_SIZE = 24f
        private const val INPUT_SERIES_LABEL_WIDTH = 150
        private const val INPUT_REPS_LABEL_WIDTH = 175
        private const val INPUT_LABEL_HEIGHT = 90
    }

    var reps: Int = 0
    var series: Int = 0
    var bodyTypeId: Long = 0

    var rows: MutableList<LinearLayout> = ArrayList()

    private val NUMBER_MIN = 1
    private val NUMBER_SERIES_MAX = 30
    private val NUMBER_REPS_MAX = 99

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {

            reps = it.getInt(REPS)
            series = it.getInt(SERIES)
            bodyTypeId = it.getLong(BODY_TYPE_ID)
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
            updateContainer(view)
        }
        return view
    }

    private fun updateContainer(view: View) {
        if (series > 0) {
            context?.let {
                createMissingRows(it)
                var layout = LinearLayout(context)

                layout.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                layout.orientation = LinearLayout.VERTICAL

                for (index in 0 until series) {
                    val row = rows.get(index.toInt())

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
