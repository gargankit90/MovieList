package com.ankit.movielist.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ankit.movielist.R
import com.ankit.movielist.databinding.SearchItemBinding
import com.ankit.movielist.search.model.Search
import com.bumptech.glide.Glide

class SearchAdapter :
    PagingDataAdapter<Search, SearchAdapter.SearchViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemBinding = SearchItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bindSearch(it) }
    }

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
}

class DiffUtilCallBack : DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem.imdbID == newItem.imdbID
    }

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem.imdbID == newItem.imdbID &&
            oldItem.title == newItem.title &&
            oldItem.year == newItem.year &&
            oldItem.type == newItem.type
    }
}
