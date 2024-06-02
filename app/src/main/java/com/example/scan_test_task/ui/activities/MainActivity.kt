package com.example.scan_test_task.ui.activities

import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scan_test_task.R
import com.example.scan_test_task.databinding.ActivityMainBinding
import com.example.scan_test_task.domain.application.ScanApplication
import com.example.scan_test_task.domain.use_cases.GetSectionsUseCase
import com.example.scan_test_task.ui.adapters.SectionListAdapter
import com.example.scan_test_task.ui.adapters.item_decorations.SectionItemDecoration
import com.example.scan_test_task.ui.view_models.SectionViewModel
import com.example.scan_test_task.ui.view_models.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private lateinit var adapter: SectionListAdapter

    private lateinit var sectionViewModel: SectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomSheet = findViewById<LinearLayout>(R.id.bottom_sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.peekHeight =
            resources.getDimension(R.dimen.bottom_sheet_peek_height).toInt()

        adapter = SectionListAdapter(resources)

        binding.sectionList.adapter = adapter
        binding.sectionList.layoutManager = LinearLayoutManager(this)
        binding.sectionList.addItemDecoration(
            SectionItemDecoration(
                resources.getDimension(R.dimen.section_list_spacing).toInt()
            )
        )

        val viewModelProvider =
            ViewModelProvider(
                this,
                ViewModelFactory(
                    GetSectionsUseCase((application as ScanApplication).database.sectionDao())
                )
            )

        sectionViewModel = viewModelProvider[SectionViewModel::class.java]

        sectionViewModel.sections.observe(this) {
            adapter.submitList(it)
        }

        sectionViewModel.getSections()
    }
}