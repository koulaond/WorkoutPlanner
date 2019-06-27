package com.example.workoutplanner.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "template")
data class Template(@PrimaryKey var id: Long, @ColumnInfo(name = "series") var series: Long, @ColumnInfo(name = "reps") var reps: Long){
    override fun toString(): String {
        return "${series}x${reps}"
    }
}