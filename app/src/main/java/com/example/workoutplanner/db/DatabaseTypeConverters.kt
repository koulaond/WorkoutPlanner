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

    @TypeConverter
    fun toMapOfInts(itemsInStr: String): Map<Int, Int> {
        var splitted: MutableMap<Int, Int> = HashMap()
        itemsInStr.split(",").forEach { substr ->
            run {
                val split = substr.split(":")
                splitted.put(split[0].toInt(), split[1].toInt())
            }
        }
        return splitted
    }

    @TypeConverter
    fun fromMapOfInts(items: Map<Int, Int>): String {
        return items.map { entry -> "${entry.key}:${entry.value}" }.joinToString(",")
    }
}