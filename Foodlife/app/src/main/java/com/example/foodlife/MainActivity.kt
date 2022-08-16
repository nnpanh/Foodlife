package com.example.foodlife

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
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
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        navView.setOnNavigationItemSelectedListener {
            changeFragment(it.itemId);
        }
    }
    private fun hideSupportActionBar() {
        supportActionBar?.hide();
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(R.id.addRecipeDirectionsFragment.toString().equals(navController.currentBackStackEntry?.destination?.id.toString()))
            navController.navigate(R.id.returnHome)
        /*Log.e("id", R.id.addRecipeTitleFragment.toString())
        Log.e("Current id", navController.currentBackStackEntry?.destination?.id.toString())
        Log.e("Back pressed", navController.previousBackStackEntry?.destination?.id.toString())*/
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
    //hide keyboard when touch inside fragment
    fun hideKeyboard() {
        val view= this.currentFocus
        if (view !=null){
            val hideMe=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)
        }
        else
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    fun showKeyboard(){
        val view= this.currentFocus
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
    //hide keyboard when touch outside fragment
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }

        if (ev!!.action === MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view != null && view is EditText) {
                val r = Rect()
                view.getGlobalVisibleRect(r)
                val rawX = ev!!.rawX.toInt()
                val rawY = ev!!.rawY.toInt()
                if (!r.contains(rawX, rawY)) {
                    view.clearFocus()
                }
            }
        }

        return super.dispatchTouchEvent(ev)
    }

}