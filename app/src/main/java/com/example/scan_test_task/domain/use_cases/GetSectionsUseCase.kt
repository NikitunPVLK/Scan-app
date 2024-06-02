package com.example.scan_test_task.domain.use_cases

import com.example.scan_test_task.domain.dao.SectionDao
import com.example.scan_test_task.domain.models.Section

class GetSectionsUseCase(private val sectionDao: SectionDao) {
    suspend fun getSections(): List<Section> {
        return sectionDao.getSections()
    }
}