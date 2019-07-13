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
import android.widget.*
import com.example.workoutplanner.utils.ViewFactory.Companion.editText
import com.example.workoutplanner.utils.ViewFactory.Companion.textView

class StandardRepsFragment : Fragment() {

    var reps: Long = 0
    var series: Long = 0
    var bodyTypeId: Long = 0

    var rows: MutableList<LinearLayout> = ArrayList()

    private val REPS = "reps"

    private val SERIES = "series"

    private val BODY_TYPE_ID = "bodyTypeId"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {

            reps = it.getLong(REPS)
            series = it.getLong(SERIES)
            bodyTypeId = it.getLong(BODY_TYPE_ID)
        }

        val view: View = inflater.inflate(R.layout.fragment_standard_reps, container, false)
        view.let {
            it.findViewById<EditText>(R.id.series_reps_series_input)?.let {
                it.setText(series.toString())
                it.addTextChangedListener(object : TextWatcher {

                    override fun afterTextChanged(editable: Editable?) {
                        val value = it.text.toString()

                        if (value.isNotBlank()) {
                            series = value.toLong()
                        } else {
                            series = 1
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

                layout.layoutParams =
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                layout.orientation = LinearLayout.VERTICAL
                layout.removeAllViewsInLayout()

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

    private fun createMissingRows(context: Context) {
        if (rows.size < series) {
            for (index in rows.size..series - 1) {
                val rowLayout = LinearLayout(context)

                rowLayout.layoutParams =
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                rowLayout.orientation = LinearLayout.HORIZONTAL

                val label: TextView = textView(
                    "Series ${index + 1}", 24f, resources.getColor(R.color.inputFieldWhite), 150, 90, context
                )

                val repsInput = editText(
                    24f,
                    getResources().getColor(R.color.inputFieldWhite),
                    150,
                    90,
                    InputType.TYPE_CLASS_NUMBER,
                    context
                )
                repsInput.setText(reps.toString())

                rowLayout.addView(label)
                rowLayout.addView(repsInput)
                rows.add(rowLayout)
            }
        }
    }
}
