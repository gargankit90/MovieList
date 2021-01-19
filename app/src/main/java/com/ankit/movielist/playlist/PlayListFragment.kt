package com.ankit.movielist.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ankit.movielist.databinding.FragmentPlayListBinding
import com.ankit.movielist.di.playlist.inject
import com.ankit.movielist.di.search.inject
import com.ankit.movielist.search.model.Search
import com.ankit.movielist.ui.viewLifecycle
import javax.inject.Inject

class PlayListFragment : Fragment() {

    private var binding: FragmentPlayListBinding by viewLifecycle()

    @Inject
    lateinit var viewModel: PlayListViewModel

    private val adapter: PlayListAdapter = PlayListAdapter()

    private val playListItemsObserver = Observer<List<Search>?> {
        if (it != null) {
            adapter.playListItems = it
            adapter.notifyDataSetChanged()
        }

        if (it.isNullOrEmpty()) {
            binding.emptyState.visibility = View.VISIBLE
        } else {
            binding.emptyState.visibility = View.GONE
        }
    }

    init {
        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.playListItems.observe(viewLifecycleOwner, playListItemsObserver)
        viewModel.getAllItems()
        binding.playListRecyclerView.adapter = adapter
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.playListRecyclerView.layoutManager = gridLayoutManager
    }
}
