package com.example.starterCode.presenter.rickyandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.starterCode.R
import com.example.starterCode.databinding.ItemRickyAndMortyBinding
import com.example.starterCode.domain.model.RickyAndMorty

class RickyAndMortyAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<RickyAndMorty, RickyAndMortyAdapter.PhotoViewHolder>(OBJECT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemRickyAndMortyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemRickyAndMortyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(item: RickyAndMorty) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.image)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_error_24)
                    .into(imageView)

                titleTextView.text = item.name
                genderTextView.text = item.gender
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: RickyAndMorty)
    }

    companion object {
        private val OBJECT_COMPARATOR = object : DiffUtil.ItemCallback<RickyAndMorty>() {
            override fun areItemsTheSame(oldItem: RickyAndMorty, newItem: RickyAndMorty) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RickyAndMorty, newItem: RickyAndMorty) =
                oldItem == newItem
        }
    }
}