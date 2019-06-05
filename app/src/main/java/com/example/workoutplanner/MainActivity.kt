package com.example.workoutplanner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init listeners for each button
        btnTrainingPlans.setOnClickListener {
            var trainingsIntent = Intent(this, TrainingsActivity::class.java)
            startActivity(trainingsIntent)
        }
        btnWorkouts.setOnClickListener {
            var workoutsIntent = Intent(this, WorkoutsActivity::class.java)
            startActivity(workoutsIntent)
        }
        btnExercises.setOnClickListener {
            var exercisesIntent = Intent(this, ExercisesActivity::class.java)
            startActivity(exercisesIntent)
        }
    }

}
