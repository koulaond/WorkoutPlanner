package com.example.workoutplanner.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable

@Entity(tableName = "exercise_body_type")
data class ExerciseBodyType(
    @PrimaryKey var id: Long,
    @ColumnInfo(name = "label") var label: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "active_parts") var activeParts: List<String>
    ) : Serializable {
    override fun toString(): String {
        return label
    }
}
