package es.iesfranciscodelosrios.calificaciones.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iesfranciscodelosrios.calificaciones.databinding.InfoActivityBinding


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