package com.example.proyecto2.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto2.R
import com.example.proyecto2.adapter.ExamenAdapter
import com.example.proyecto2.databinding.ListActivityBinding
import com.example.proyecto2.model.Examen

class ListActivity: AppCompatActivity() {
    private lateinit var binding: ListActivityBinding

    private var examList: ArrayList<Examen> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.examRV.layoutManager = LinearLayoutManager(this)
        binding.examRV.adapter = ExamenAdapter(examList, onItemClicked = { exam ->
            val i = Intent(this, FormActivity::class.java)
            i.putExtra("examId", exam.id)
            startActivity(i)
        })

        binding.btnNew.setOnClickListener {
            val i = Intent(this, FormActivity::class.java)
            startActivity(i)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val i = Intent(this, InfoActivity::class.java)
        startActivity(i)
        return when (item.itemId) {
            R.id.menu_item -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}