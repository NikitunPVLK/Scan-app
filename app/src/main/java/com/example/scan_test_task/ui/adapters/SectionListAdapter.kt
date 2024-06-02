package com.example.scan_test_task.ui.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.scan_test_task.R
import com.example.scan_test_task.databinding.SectionItemBinding
import com.example.scan_test_task.domain.models.Section

class SectionListAdapter(
    private val resources: Resources
) : ListAdapter<Section, SectionListAdapter.SectionViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SectionViewHolder {
        return SectionViewHolder(
            SectionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            resources
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class SectionViewHolder(
        private val binding: SectionItemBinding,
        private val resources: Resources
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(section: Section) {
            with(binding) {
                if (section.alerts != 0) {
                    backgroundView.background =
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.alert_section_shape,
                            null
                        )
                    alertText.text = section.alerts.toString()
                    alertText.visibility = View.VISIBLE
                }
                sectionName.text = resources.getString(section.nameId)
                sectionInfo.text = resources.getString(section.infoId)
                sectionIcon.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        section.imageId,
                        null
                    )
                )
            }
        }
    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<Section>() {
            override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
                return oldItem.nameId == newItem.nameId
            }
        }
    }
}