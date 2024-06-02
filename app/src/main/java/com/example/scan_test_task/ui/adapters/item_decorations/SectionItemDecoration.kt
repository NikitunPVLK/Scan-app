package com.example.scan_test_task.ui.adapters.item_decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SectionItemDecoration(private val bottomSpacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = bottomSpacing
    }
}