package com.example.workoutplanner.utils

import android.content.Context
import android.content.res.ColorStateList
import android.text.InputType
import android.widget.EditText
import android.widget.TextView

class ViewFactory {

    companion object {

        fun textView(
            text: String,
            textSize: Float,
            textColor: Int,
            width: Int,
            height: Int,
            context: Context
        ): TextView {

            val textView: TextView = TextView(context)
            textView.text = text
            textView.textSize = textSize
            textView.setTextColor(textColor)
            val scale = context.resources.displayMetrics.density
            val widthInPixels = (width * scale + 0.5f).toInt()
            textView.width = widthInPixels
            textView.height = height
            return textView
        }

        fun editText(
            textSize: Float,
            textColor: Int,
            width: Int,
            height: Int,
            inputType: Int,
            context: Context
        ): EditText {

            val editText = EditText(context)
            editText.textSize = textSize
            editText.setTextColor(textColor)
            editText.backgroundTintList = ColorStateList.valueOf(textColor)
            val scale = context.resources.displayMetrics.density
            val widthInPixels = (width * scale + 0.5f).toInt()
            editText.width = widthInPixels
            editText.height = height
            editText.inputType = inputType
            editText.tag = "REP_INPUT"
            return editText
        }
    }
}