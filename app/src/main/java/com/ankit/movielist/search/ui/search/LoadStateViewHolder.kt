package com.ankit.movielist.search.ui.search

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.ankit.movielist.databinding.LoadStateItemBinding

class LoadStateViewHolder(
    private val itemBinding: LoadStateItemBinding
) : RecyclerView.ViewHolder(
    itemBinding.root
) {

    fun bind(loadState: LoadState) {
        with(itemBinding) {
            if (loadState is LoadState.Error) {
                errorMessage.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            errorMessage.isVisible = loadState is LoadState.Error
        }
    }
}
