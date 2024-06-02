package com.example.scan_test_task.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scan_test_task.domain.use_cases.GetSectionsUseCase

class ViewModelFactory(private val getSectionsUseCase: GetSectionsUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SectionViewModel(
                getSectionsUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass::class.simpleName)
    }
}