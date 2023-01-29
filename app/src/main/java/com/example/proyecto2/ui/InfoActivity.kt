package com.example.proyecto2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto2.databinding.InfoActivityBinding

class InfoActivity: AppCompatActivity() {
    private lateinit var binding: InfoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InfoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}