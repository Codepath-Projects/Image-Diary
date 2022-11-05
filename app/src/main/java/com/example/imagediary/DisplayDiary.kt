package com.example.imagediary

import androidx.room.ColumnInfo
import java.util.*

data class DisplayDiary(
    val title: String,
    val date: String,  // TODO: FIX
    val description: String,
    val photo_path: String?  // TODO: FIX
) : java.io.Serializable
