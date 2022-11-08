package com.example.imagediary

import java.io.Serializable
import java.sql.Date

data class Diary_Entry(var title: String, var date: String, var description: String
) : Serializable