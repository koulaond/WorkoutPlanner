package com.example.workoutplanner.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutplanner.R

class WorkoutPlannerViewHolder: RecyclerView.ViewHolder {
    val exerciseNameView : TextView
    val exerciseBodyTypeView : TextView
    val seriesAndRepsView : TextView

    constructor(itemView: View) : super(itemView) {
        exerciseNameView = itemView.findViewById(R.id.exerciseName)
        exerciseBodyTypeView = itemView.findViewById(R.id.exerciseBodyType)
        seriesAndRepsView = itemView.findViewById(R.id.exerciseSeriesAndReps)
    }
}