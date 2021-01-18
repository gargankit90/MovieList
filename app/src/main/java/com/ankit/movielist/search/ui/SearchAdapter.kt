package com.ankit.movielist.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ankit.movielist.databinding.SearchItemBinding
import com.ankit.movielist.search.model.Search

class SearchAdapter(
    private val onSearchItemClickListener: SearchItemOnClickListener
) :
    PagingDataAdapter<Search, SearchViewHolder>(DiffUtilCallBack()) {

    companion object {
        // Define Loading ViewType
        const val LOADING_ITEM = 0

        // Define Search ViewType
        const val SEARCH_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemBinding = SearchItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(itemBinding, onSearchItemClickListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bindSearch(it) }
    }

    override fun getItemViewType(position: Int): Int {
        // set ViewType
        return if (position == itemCount) SEARCH_ITEM else LOADING_ITEM
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

interface SearchItemOnClickListener {
    fun onSearchItemClicked(searchItem: Search)
}
