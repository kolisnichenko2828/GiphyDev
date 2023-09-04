package com.example.giphydev.presenter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphydev.R
import com.example.giphydev.databinding.LayoutRvListBinding
import com.example.giphydev.domain.models.Gifs

class AdapterForRvList(private val context: Context,
                       private val vm: MainViewModel): RecyclerView.Adapter<AdapterForRvList.ViewHolderForRecyclerView>() {
    private var gifs: Gifs = Gifs (MutableList(25) { "" })

    class ViewHolderForRecyclerView(val binding: LayoutRvListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderForRecyclerView {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRvListBinding.inflate(inflater, parent, false)
        return ViewHolderForRecyclerView(binding)
    }

    override fun getItemCount(): Int {
        return gifs.url.size
    }

    override fun onBindViewHolder(holder: ViewHolderForRecyclerView, position: Int) {
        with(holder.binding) {
            Glide.with(context)
                .load(gifs.url[position])
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)

            imageView.setOnClickListener() {
                vm.position = position
                vm.changeFragmentTo("SingleGifFragment")
                vm.changeFragmentTo("Default")
            }
        }
    }

    fun setGifs(list: Gifs) {
        gifs = list
        notifyDataSetChanged()
    }
}