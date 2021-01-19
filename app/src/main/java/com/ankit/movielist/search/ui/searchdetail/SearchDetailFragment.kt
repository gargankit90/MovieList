package com.ankit.movielist.search.ui.searchdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ankit.movielist.R
import com.ankit.movielist.databinding.FragmentSearchDetailBinding
import com.ankit.movielist.di.search.inject
import com.ankit.movielist.ui.viewLifecycle
import com.bumptech.glide.Glide
import javax.inject.Inject

class SearchDetailFragment : Fragment() {

    private val arguments: SearchDetailFragmentArgs by navArgs()
    private var binding: FragmentSearchDetailBinding by viewLifecycle()
    private val itemExistInPlayListObserver = Observer<Boolean> {
        if (it) {
            showRemoveFromPlayList()
        } else {
            showAddToPlayList()
        }
    }

    private val addItemInPlayListObserver = Observer<Boolean> {
        if (it) {
            showRemoveFromPlayList()
        }
    }

    private val removeItemInPlayListObserver = Observer<Boolean> {
        if (it) {
            showAddToPlayList()
        }
    }

    @Inject
    lateinit var viewModel: SearchDetailViewModel

    init {
        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchItem = arguments.searchItem
        viewModel.itemExists.observe(viewLifecycleOwner, itemExistInPlayListObserver)
        viewModel.checkIfItemInPlayList(searchItem)

        binding.searchDetailAddToPlayList.setOnClickListener {
            viewModel.addItemInPlayList(searchItem)
        }
        viewModel.addItem.observe(viewLifecycleOwner, addItemInPlayListObserver)

        binding.searchDetailRemoveFromPlayList.setOnClickListener {
            viewModel.removeItemFromPlayList(searchItem)
        }
        viewModel.removeItem.observe(viewLifecycleOwner, removeItemInPlayListObserver)

        binding.searchDetailTitle.text = searchItem.title
        binding.searchDetailYear.text = searchItem.year
        Glide.with(requireContext())
            .load(searchItem.poster)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .into(binding.searchDetailPoster)
    }

    private fun showAddToPlayList() {
        binding.searchDetailRemoveFromPlayList.visibility = View.GONE
        binding.searchDetailAddToPlayList.visibility = View.VISIBLE
    }

    private fun showRemoveFromPlayList() {
        binding.searchDetailRemoveFromPlayList.visibility = View.VISIBLE
        binding.searchDetailAddToPlayList.visibility = View.GONE
    }
}
