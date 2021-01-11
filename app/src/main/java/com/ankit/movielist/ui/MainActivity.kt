package com.ankit.movielist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ankit.movielist.R
import com.ankit.movielist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.navHostFragment)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottomNavigationSearchMenuId -> {
                    navController
                        .navigate(
                            R.id.searchFragment
                        )
                    true
                }
                R.id.bottomNavigationPlayListMenuId -> {
                    navController
                        .navigate(
                            R.id.playListFragment
                        )
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}
