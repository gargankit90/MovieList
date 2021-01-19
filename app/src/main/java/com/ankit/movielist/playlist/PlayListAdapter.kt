package com.ankit.movielist.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ankit.movielist.databinding.SearchItemBinding
import com.ankit.movielist.search.model.Search

class PlayListAdapter : RecyclerView.Adapter<PlayListViewHolder>() {

    var playListItems = listOf<Search>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val itemBinding = SearchItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        val item = playListItems.get(position)
        holder.bind(item)
    }

    override fun getItemCount() = playListItems.size
}
