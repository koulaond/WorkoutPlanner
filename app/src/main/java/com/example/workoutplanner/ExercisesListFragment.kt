package com.example.workoutplanner


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.workoutplanner.domain.ExerciseDefinition
import com.example.workoutplanner.dto.ExerciseDto
import com.example.workoutplanner.list.WorkoutsPlannerRecyclerViewAdapter
import com.example.workoutplanner.view.BodyPartViewModel
import com.example.workoutplanner.view.ExerciseDefinitionViewModel
import kotlinx.android.synthetic.main.fragment_exercises_list.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ExercisesListFragment : Fragment() {

    private lateinit var exerciseDefinitionViewModel: ExerciseDefinitionViewModel
    private lateinit var bodyPartViewModel: BodyPartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercises_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelProvider = ViewModelProviders.of(this)
        exerciseDefinitionViewModel = viewModelProvider.get(ExerciseDefinitionViewModel::class.java)
        bodyPartViewModel = viewModelProvider.get(BodyPartViewModel::class.java)


        exerciseDefinitionViewModel.allExerciseDefinitions.observe(this, Observer { it ->

            var dtos: MutableList<ExerciseDto> = ArrayList()
            it.forEach { exerciseDefinition ->
                val found = it.find {
                }
                found?.let { item ->
                    var exerciseDto = ExerciseDto(
                        exerciseDefinition.id,
                        exerciseDefinition.name,
                        exerciseDefinition.series,
                        item.exerciseBodyType.label
                    )

                    dtos.add(exerciseDto)
                }
            }
            exercisesListView.apply { adapter = WorkoutsPlannerRecyclerViewAdapter(dtos) }
        })
    }

}
