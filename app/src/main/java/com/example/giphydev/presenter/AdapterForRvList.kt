package com.example.giphydev.presenter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphydev.R
import com.example.giphydev.databinding.LayoutRvListBinding
import com.example.giphydev.domain.models.Gifs

class GifsAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<String, GifsAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(
        private val binding: LayoutRvListBinding,
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(url: String, listener: OnItemClickListener) {
            Glide.with(itemView.context)
                .load(url)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imageView)

            binding.imageView.setOnClickListener() {
                listener.onItemClick(url)
            }
        }

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(LayoutRvListBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(url = getItem(position), listener = listener)
    }
}