package com.example.scan_test_task.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.scan_test_task.domain.dao.SectionDao
import com.example.scan_test_task.domain.models.Section

@Database(entities = [Section::class], version = 1, exportSchema = false)
abstract class SectionRoomDatabase : RoomDatabase() {
    abstract fun sectionDao() : SectionDao
}