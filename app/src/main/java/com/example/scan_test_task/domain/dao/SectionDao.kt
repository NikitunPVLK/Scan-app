package com.example.scan_test_task.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.scan_test_task.domain.models.Section

@Dao
interface SectionDao {
    @Insert
    suspend fun insert(section: Section)

    @Query("SELECT * FROM section")
    suspend fun getSections(): List<Section>
}