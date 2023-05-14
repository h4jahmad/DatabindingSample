package com.example.databindingsample.home.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.databindingsample.common.util.OnItemClicked
import com.example.databindingsample.databinding.ItemImageListBinding
import com.example.databindingsample.home.ui.models.ImageListItem

class ImageListItemViewHolder(
    private val binding: ItemImageListBinding,
    private val onItemClicked: OnItemClicked<ImageListItem>,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageListItem) = with(binding) {
        root.setOnClickListener { onItemClicked(item) }
        this.item = item
    }
}