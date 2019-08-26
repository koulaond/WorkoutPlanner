package com.example.workoutplanner


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.workoutplanner.domain.ExerciseDefinition
import com.example.workoutplanner.dto.ExerciseDto
import com.example.workoutplanner.list.WorkoutsPlannerRecyclerViewAdapter
import com.example.workoutplanner.view.BodyPartViewModel
import com.example.workoutplanner.view.ExerciseDefinitionViewModel
import kotlinx.android.synthetic.main.fragment_exercises.*
import kotlinx.android.synthetic.main.fragment_exercises_list.*
import kotlinx.android.synthetic.main.layout_listitem.*
import java.util.stream.Stream

class ExercisesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercises, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCreateExercise.setOnClickListener {
            it.findNavController().navigate(R.id.action_to_select_exercise_type)
        }
    }
}
