package com.example.foodlife

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodlife.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null

        navController = findNavController(R.id.nav_host_fragment_activity_main)
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

        navView.setOnNavigationItemSelectedListener {
            changeFragment(it.itemId);
        }
    }
    private fun hideSupportActionBar() {
        supportActionBar?.hide();
    }

    private fun changeFragment(item: Int): Boolean {
        when(item){
            R.id.navigation_home -> {
                for (i in 0 until supportFragmentManager.backStackEntryCount) {
                    supportFragmentManager.popBackStack()
                }

                navController.navigate(R.id.returnHome)
                return true
            }
            R.id.navigation_plan -> {
                for (i in 0 until supportFragmentManager.backStackEntryCount) {
                    supportFragmentManager.popBackStack()
                }
                navController.navigate(R.id.returnPlan)
                return true
            }
            R.id.navigation_profile -> {
                for (i in 0 until supportFragmentManager.backStackEntryCount) {
                    supportFragmentManager.popBackStack()
                }
                navController.navigate(R.id.returnProfile)
                return true
            }
            R.id.navigation_collections -> {
                for (i in 0 until supportFragmentManager.backStackEntryCount) {
                    supportFragmentManager.popBackStack()
                }
                navController.navigate(R.id.returnCollection)
                return true
            }
        }
        return false
    }
    fun hideKeyboard() {
        /*val inputManager: InputMethodManager = activity
            .getSystemService<Any>(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // check if no view has focus:
        val currentFocusedView: View? = activity.currentFocus
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(
                currentFocusedView.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }*/
        var view= this.currentFocus
        if (view !=null){
            val hideMe=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)
        }
        else
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}