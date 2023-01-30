package es.iesfranciscodelosrios.calificaciones.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import es.iesfranciscodelosrios.calificaciones.R

import es.iesfranciscodelosrios.calificaciones.adapter.ExamenAdapter
import es.iesfranciscodelosrios.calificaciones.databinding.ListActivityBinding
import es.iesfranciscodelosrios.calificaciones.model.DataBaseHelper

import es.iesfranciscodelosrios.calificaciones.model.Examen
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ListActivity: AppCompatActivity() {
    private lateinit var binding: ListActivityBinding

    private var examList: ArrayList<Examen> = arrayListOf()
    private var db: DataBaseHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListActivityBinding.inflate(layoutInflater)
        examList = db.getAllExamenes()
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

    override fun onResume() {
        super.onResume()
        examList = db.getAllExamenes()
        binding.examRV.adapter = ExamenAdapter(examList, onItemClicked = { exam ->
            val i = Intent(this, FormActivity::class.java)
            i.putExtra("examId", exam.id)
            startActivity(i)
        })
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