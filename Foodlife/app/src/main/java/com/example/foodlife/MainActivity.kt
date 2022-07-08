package com.example.foodlife

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TintContextWrapper
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodlife.databinding.ActivityMainBinding
import com.example.foodlife.fragments.RecommendFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_plan,
                R.id.navigation_collections,
                R.id.navigation_profile,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Custom
        hideSupportActionBar();
//        navView.itemIconTintList = null

        val IVBack = findViewById<ImageView>(R.id.ivArrow)
        IVBack.setOnClickListener {
            val firstFragment=RecommendFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_activity_main, firstFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
    private fun hideSupportActionBar() {
        supportActionBar?.hide();
    }
}