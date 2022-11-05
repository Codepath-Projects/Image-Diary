package com.example.imagediary

import androidx.room.ColumnInfo
import java.util.*

data class DisplayDiary(
    val title: String,
    val date: String,
    val description: String,
    val photo_path: String?
) : java.io.Serializable
