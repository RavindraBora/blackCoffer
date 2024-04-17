package com.example.blackcoffer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blackcoffer.R
import com.example.blackcoffer.databinding.ActivityProfileBinding

class ActivityProfile : AppCompatActivity() {
    lateinit var binding : ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}