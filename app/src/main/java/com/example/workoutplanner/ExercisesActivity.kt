package com.example.workoutplanner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.workoutplanner.exercise.SelectTypeActivity
import kotlinx.android.synthetic.main.activity_exercises.*

class ExercisesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        btnCreateExercise.setOnClickListener {
            var selectTypeIntent = Intent(this, SelectTypeActivity::class.java)
            startActivity(selectTypeIntent)
        }

    }
}
