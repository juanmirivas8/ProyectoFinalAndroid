package com.example.proyecto2.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto2.databinding.FormActivityBinding

class FormActivity: AppCompatActivity() {
    private lateinit var binding: FormActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        if (intent.hasExtra("contactId")) {
            val id: String? = intent.getStringExtra("contactId")
            binding.InsertBTN.visibility = View.GONE
        }else{
            binding.InsertBTN.setOnClickListener{

            }
        }
    }
}