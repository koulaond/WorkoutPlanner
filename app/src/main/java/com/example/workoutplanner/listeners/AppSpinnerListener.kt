package com.example.workoutplanner.listeners

import android.view.View
import android.widget.AdapterView

class AppSpinnerListener<T> : AdapterView.OnItemSelectedListener {

    var items: Array<T>
    var selectedItem: T
    var emptyValue: T

    constructor(items: Array<T>, emptyValue: T) {
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