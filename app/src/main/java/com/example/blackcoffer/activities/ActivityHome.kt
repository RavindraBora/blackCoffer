package com.example.blackcoffer.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blackcoffer.adapter.AdapterViewPager
import com.example.blackcoffer.databinding.ActivityHomeBinding

class ActivityHome : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llRefine.setOnClickListener {
            val intent = Intent(this, ActivityRefine::class.java)
            startActivity(intent)
        }

        val adapter = AdapterViewPager(supportFragmentManager)

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }
}