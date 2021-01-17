package com.ankit.movielist.search.ui

import androidx.recyclerview.widget.RecyclerView
import com.ankit.movielist.R
import com.ankit.movielist.databinding.SearchItemBinding
import com.ankit.movielist.search.model.Search
import com.bumptech.glide.Glide

class SearchViewHolder(private val itemBinding: SearchItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bindSearch(item: Search) {
        itemBinding.movieTitle.text = item.title
        itemBinding.movieYear.text = item.year
        Glide.with(itemBinding.root)
            .load(item.poster)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .into(itemBinding.moviePoster)
    }
}
