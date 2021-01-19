package com.ankit.movielist.search.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.ankit.movielist.R
import com.ankit.movielist.databinding.FragmentSearchBinding
import com.ankit.movielist.di.search.inject
import com.ankit.movielist.isNetworkAvailable
import com.ankit.movielist.search.model.Search
import com.ankit.movielist.ui.viewLifecycle
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModel: SearchViewModel

    private val adapter: SearchAdapter = SearchAdapter(
        object : SearchItemOnClickListener {
            override fun onSearchItemClicked(searchItem: Search) {
                val navDirection = SearchFragmentDirections
                    .actionSearchFragmentToSearchDetailFragment(
                        searchItem
                    )
                findNavController().navigate(navDirection)
            }
        }
    )

    private var binding: FragmentSearchBinding by viewLifecycle()
    private var userQuery: String? = null

    init {
        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        initializeSearchBox()
    }

    private fun initializeRecyclerView() {
        adapter.addLoadStateListener {
            val loadState = it.refresh
            if (loadState is LoadState.Error) {
                Snackbar.make(
                        binding.root,
                        getString(R.string.something_went_wrong),
                        Snackbar.LENGTH_LONG
                ).show()

            }
        }
        binding.searchResultRecyclerView.adapter =
            adapter.withLoadStateFooter(LoadingStateAdapter())
        initializeLayoutManager()
    }

    private fun initializeLayoutManager() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        // set Grid span
        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // If progress will be shown then span size will be 1 otherwise it will be 2
                return if (adapter.getItemViewType(position)
                    == SearchAdapter.LOADING_ITEM
                ) 1 else 2
            }
        }
        binding.searchResultRecyclerView.layoutManager = gridLayoutManager
    }

    private fun initializeSearchBox() {
        binding.searchView.isIconifiedByDefault = false
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                userQuery = query
                userQuery?.let {
                    if (isNetworkAvailable(requireContext())) {
                        getQueryResults(it)
                    } else {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.network_not_available),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    private fun getQueryResults(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getSearchResults(query.toString()).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
