package com.example.workoutplanner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

open class BaseActivity : AppCompatActivity() {

    val BASE_ACT_TAG = "Lifecycle log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(BASE_ACT_TAG, "${javaClass.simpleName} onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(BASE_ACT_TAG, "${javaClass.simpleName} onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(BASE_ACT_TAG, "${javaClass.simpleName} onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(BASE_ACT_TAG, "${javaClass.simpleName} onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(BASE_ACT_TAG, "${javaClass.simpleName} onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(BASE_ACT_TAG, "${javaClass.simpleName} onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(BASE_ACT_TAG, "${javaClass.simpleName} onDestroy")
    }
}
