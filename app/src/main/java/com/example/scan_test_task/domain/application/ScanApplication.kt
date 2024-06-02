package com.example.scan_test_task.domain.application

import android.app.Application
import com.example.scan_test_task.data.DatabaseProvider

class ScanApplication : Application() {
    val database by lazy {
        DatabaseProvider.getDatabase(this)
    }
}