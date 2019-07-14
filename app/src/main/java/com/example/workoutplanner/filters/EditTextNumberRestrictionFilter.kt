package com.example.workoutplanner.filters

import android.text.InputFilter
import android.text.Spanned

class EditTextNumberRestrictionFilter : InputFilter {

    val min: Int
    val max: Int

   constructor( min: Int, max: Int) {
       if (min <= max) {
           this.min = min
           this.max = max
       } else {
           this.min = max
           this.max = min
       }
   }
    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int)
            : CharSequence {

        val dest = dest.toString()
        val source = source.toString()

        if (source.isBlank() && dest.length == 1) {
            return  ""
        }

        val input = Integer.parseInt(dest + source)
        return if (input >= min && input <= max) source else ""
    }
}