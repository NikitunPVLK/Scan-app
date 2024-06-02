package com.example.scan_test_task.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "section")
data class Section(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameId: Int,
    val infoId: Int,
    val imageId: Int,
    var alerts: Int = 0
)
