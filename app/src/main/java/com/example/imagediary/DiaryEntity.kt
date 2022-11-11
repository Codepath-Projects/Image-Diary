package com.example.imagediary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*

@Entity(tableName = "diary_table")

data class DiaryEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "photo_path") val photo_path: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)