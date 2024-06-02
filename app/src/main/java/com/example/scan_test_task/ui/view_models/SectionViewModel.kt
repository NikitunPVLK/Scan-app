package com.example.scan_test_task.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scan_test_task.domain.models.Section
import com.example.scan_test_task.domain.use_cases.GetSectionsUseCase
import kotlinx.coroutines.launch

class SectionViewModel(private val getSectionsUseCase: GetSectionsUseCase): ViewModel() {
    private val _sections = MutableLiveData<List<Section>>()
    val sections: LiveData<List<Section>>
        get() = _sections

    fun getSections() {
        viewModelScope.launch {
            _sections.value = getSectionsUseCase.getSections()
        }
    }
}