package com.example.workoutplanner.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutplanner.R
import com.example.workoutplanner.dto.ExerciseDto

class WorkoutsPlannerRecyclerViewAdapter(var exercises: List<ExerciseDto>) : RecyclerView.Adapter<WorkoutPlannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutPlannerViewHolder {

        var view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.layout_listitem, parent, false) as TextView

        var viewHolder = WorkoutPlannerViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: WorkoutPlannerViewHolder, position: Int) {
        val exercise = exercises.get(position)
        holder.exerciseNameView.setText(exercise.name)
        holder.seriesAndRepsView.setText(exercise.seriesAndReps.toString())
        holder.exerciseBodyTypeView.setText(exercise.bodyType)
    }

}