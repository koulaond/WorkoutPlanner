package com.example.workoutplanner.db

import androidx.room.TypeConverter

class DatabaseTypeConverters {

    @TypeConverter
    fun fromListOfStrings(items: List<String>): String {
        return items.joinToString(",")
    }

    @TypeConverter
    fun toListOfStrings(itemsInStr: String): List<String> {
        return itemsInStr.split(",")
    }
}