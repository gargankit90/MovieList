package com.ankit.movielist.search.ui.searchdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ankit.movielist.R
import com.ankit.movielist.databinding.FragmentSearchDetailBinding
import com.ankit.movielist.ui.viewLifecycle
import com.bumptech.glide.Glide
import timber.log.Timber

class SearchDetailFragment : Fragment() {

    private val arguments: SearchDetailFragmentArgs by navArgs()
    private var binding: FragmentSearchDetailBinding by viewLifecycle()

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
        binding.searchDetailTitle.text = searchItem.title
        binding.searchDetailYear.text = searchItem.year
        Glide.with(requireContext())
            .load(searchItem.poster)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .into(binding.searchDetailPoster)
    }
}
