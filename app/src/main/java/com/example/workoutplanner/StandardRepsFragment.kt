package com.example.workoutplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class StandardRepsFragment : Fragment() {

    val containerLayoutId = "containerLayout"

    var reps: Long = 0
    var series: Long = 0
    var bodyTypeId: Long = 0

    var seriesLabels: List<TextView> = ArrayList()
    var seriesInputs: List<EditText> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {

            reps = it.getLong("reps")
            series = it.getLong("series")
            bodyTypeId = it.getLong("bodyTypeId")
        }

        val view: View = inflater.inflate(R.layout.fragment_standard_reps, container, false)
        view?.let {
            it.findViewById<EditText>(R.id.series_reps_series_input)?.let {
                it.setText(series.toString())
            }
            it.findViewById<EditText>(R.id.series_reps_reps_input)?.let {
                it.setText(reps.toString())
            }
            initContainer(view)
        }
        return view
    }

    private fun initContainer(view: View) {
        var layout: LinearLayout = view.findViewById<LinearLayout>(R.id.repsContainerLayout)

        for (i in 0 until reps) {
            var label: TextView = TextView(context)
            label.setText("Series ${i+1}")
            layout.addView(label)
        }
        layout
    }
}
