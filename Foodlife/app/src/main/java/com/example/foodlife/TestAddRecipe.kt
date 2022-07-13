package com.example.foodlife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodlife.databinding.ActivityMainBinding
import com.example.foodlife.databinding.FragmentAddRecipeInformationBinding
import com.example.foodlife.databinding.FragmentAddRecipeTitleBinding

class TestAddRecipe : AppCompatActivity() {
    private lateinit var binding: com.example.foodlife.databinding.FragmentAddRecipeTitleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentAddRecipeTitleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}