package com.example.imagediary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "diary_table")

data class DiaryEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "date") val date: String,  // temp for now, check Codepath to implement Date into a database
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "photo_path") val photo_path: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)