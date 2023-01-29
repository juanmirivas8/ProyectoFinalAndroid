package es.iesfranciscodelosrios.calificaciones.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import es.iesfranciscodelosrios.calificaciones.R
import es.iesfranciscodelosrios.calificaciones.databinding.FormActivityBinding
import es.iesfranciscodelosrios.calificaciones.model.Asignatura


class FormActivity: AppCompatActivity() {
    private lateinit var binding: FormActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Asignatura.values())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.asignaturaPT.adapter = adapter
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.fechaPT.setOnClickListener {

        }

        if (!intent.hasExtra("contactId")) {
            binding.InsertBTN.text = "Guardar"
        }else{
            val id: String? = intent.getStringExtra("contactId")
            binding.InsertBTN.setOnClickListener{

            }
        }
    }
}