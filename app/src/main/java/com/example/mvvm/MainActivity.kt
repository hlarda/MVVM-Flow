package com.example.mvvm

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.Screens.All.view.AllActivity
import com.example.mvvm.Screens.Saved.view.SavedActivity
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allBtn.setOnClickListener {
            startActivity(Intent(this, AllActivity::class.java))
        }

        binding.savedBtn.setOnClickListener {
            startActivity(Intent(this, SavedActivity::class.java))
        }


    }
}