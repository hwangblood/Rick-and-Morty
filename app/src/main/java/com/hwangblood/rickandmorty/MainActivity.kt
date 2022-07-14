package com.hwangblood.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hwangblood.rickandmorty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        val navController = navHostFragment.navController
        val navDrawerView = binding.navDrawerView
        val drawerLayout = binding.drawerLayout
        val topLevelDestinationIds = setOf(R.id.characterListFragment, R.id.episodeListFragment)

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = topLevelDestinationIds,
            drawerLayout = drawerLayout
        )

        setupActionBarWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )

        navDrawerView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (topLevelDestinationIds.contains(destination.id)) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        // Set start destination selected of drawer when app launch firstly.
        navDrawerView.setCheckedItem(navController.graph.startDestinationId)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}