package com.example.imagediary

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DairyDao {
    @Query("SELECT * FROM diary_table")
    fun getAll(): Flow<List<DiaryEntity>>

    @Insert
    fun insertAll(articles: List<DiaryEntity>)

    @Insert
    fun insert(event: DiaryEntity)

    @Query("DELETE FROM diary_table")
    fun deleteAll()
}