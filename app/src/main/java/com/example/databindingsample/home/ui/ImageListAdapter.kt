package com.example.databindingsample.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.databindingsample.common.util.OnItemClicked
import com.example.databindingsample.databinding.ItemImageListBinding
import com.example.databindingsample.home.ui.models.ImageListItem

class ImageListAdapter(
    private val onItemClicked: OnItemClicked<ImageListItem>,
) : ListAdapter<ImageListItem, ImageListItemViewHolder>(
    AsyncDifferConfig.Builder(COMPARATOR).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListItemViewHolder =
        ImageListItemViewHolder(
            ItemImageListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked
        )

    override fun onBindViewHolder(holder: ImageListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<ImageListItem>() {
            override fun areItemsTheSame(oldItem: ImageListItem, newItem: ImageListItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ImageListItem,
                newItem: ImageListItem
            ): Boolean = oldItem == newItem

        }
    }
}