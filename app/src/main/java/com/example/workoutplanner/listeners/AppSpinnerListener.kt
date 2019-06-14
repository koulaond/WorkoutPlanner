package com.example.workoutplanner.listeners

import android.view.View
import android.widget.AdapterView

class AppSpinnerListener : AdapterView.OnItemSelectedListener {

    var items: Array<String>
    var selectedItem: String
    var emptyValue: String

    constructor(items: Array<String>, emptyValue: String) {
        this.items = items
        this.selectedItem = items[0]
        this.emptyValue = emptyValue
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedItem = items[position]
    }
}