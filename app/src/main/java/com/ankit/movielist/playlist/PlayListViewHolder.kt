package com.ankit.movielist.playlist

import androidx.recyclerview.widget.RecyclerView
import com.ankit.movielist.R
import com.ankit.movielist.databinding.SearchItemBinding
import com.ankit.movielist.search.model.Search
import com.bumptech.glide.Glide

class PlayListViewHolder(
    private val itemBinding: SearchItemBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: Search) {
        itemBinding.movieTitle.text = item.title
        Glide.with(itemBinding.root)
            .load(item.poster)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .into(itemBinding.moviePoster)
    }
}
