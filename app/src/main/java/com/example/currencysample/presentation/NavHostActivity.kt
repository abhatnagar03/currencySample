package com.example.currencysample.presentation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.currencysample.R
import com.example.foundation.presentation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_nav_host.*

class NavHostActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_nav_host

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navController = navHostFragment.findNavController()
        bottomNav.setupWithNavController(navController)
    }
}
