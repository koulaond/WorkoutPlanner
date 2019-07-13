package com.example.workoutplanner.filters

import android.text.InputFilter
import android.text.Spanned

class EditTextNumberRestrictionFilter(min: Int, max: Int) : InputFilter {


    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int)
            : CharSequence {
        return ""
    }
}