package com.example.scan_test_task.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.scan_test_task.R
import com.example.scan_test_task.domain.dao.SectionDao
import com.example.scan_test_task.domain.models.Section
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseProvider {
    @Volatile
    private var INSTANCE: SectionRoomDatabase? = null

    fun getDatabase(context: Context): SectionRoomDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                SectionRoomDatabase::class.java,
                "section_database"
            ).addCallback(SectionDatabaseCallback())
                .build()
            INSTANCE = instance
            instance
        }
    }

    private class SectionDatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Populate the database in the background.
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.sectionDao())
                }
            }
        }
    }

    suspend fun populateDatabase(sectionDao: SectionDao) {
        sectionDao.insert(
            Section(
                1,
                R.string.device_info,
                R.string.info_about_phone,
                R.drawable.device_info
            )
        )
        sectionDao.insert(
            Section(
                2,
                R.string.calibration,
                R.string.info_about,
                R.drawable.calibration
            )
        )
        sectionDao.insert(
            Section(
                3,
                R.string.app_monitoring,
                R.string.info_about,
                R.drawable.app_monitoring
            )
        )
        sectionDao.insert(
            Section(
                4,
                R.string.antivirus,
                R.string.info_about,
                R.drawable.antivirus,
                3
            )
        )
        sectionDao.insert(
            Section(
                5,
                R.string.device_memory,
                R.string.info_about,
                R.drawable.device_memory
            )
        )
        sectionDao.insert(
            Section(
                6,
                R.string.file_manager,
                R.string.info_about,
                R.drawable.file_manager
            )
        )
        sectionDao.insert(
            Section(
                7,
                R.string.battery_info,
                R.string.info_about,
                R.drawable.battery_info,
                2
            )
        )
    }
}
