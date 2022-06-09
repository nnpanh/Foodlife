package com.example.foodlife.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodlife.R
import com.example.foodlife.databinding.ActivityMainBinding
import com.example.foodlife.databinding.ActivityTestBinding

class Test : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            finish()
        }
    }
}