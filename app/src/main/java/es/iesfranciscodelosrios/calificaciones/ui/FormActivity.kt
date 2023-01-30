package es.iesfranciscodelosrios.calificaciones.ui


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import es.iesfranciscodelosrios.calificaciones.databinding.FormActivityBinding
import es.iesfranciscodelosrios.calificaciones.model.Asignatura
import es.iesfranciscodelosrios.calificaciones.model.DataBaseHelper
import es.iesfranciscodelosrios.calificaciones.model.Examen
import java.text.SimpleDateFormat
import java.util.*


class FormActivity: AppCompatActivity() {
    private lateinit var binding: FormActivityBinding
    private var db: DataBaseHelper = DataBaseHelper(this)
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
            showDatePickerDialog()
        }

        if (!intent.hasExtra("examId")) {
            binding.InsertBTN.text = "Guardar"
            binding.deleteBtn.visibility = View.GONE

            binding.InsertBTN.setOnClickListener{
                if (isDataValid()) {
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val exam = Examen(
                        titulo = binding.tituloPT.text.toString(),
                        fecha = dateFormat.parse(binding.fechaPT.text.toString())!!,
                        nota = binding.notaPT.text.toString().toInt(),
                        asignatura = Asignatura.getConstantByName(binding.asignaturaPT.selectedItem.toString())
                    )
                    db.addData(exam)
                    onBackPressed()
                }
            }

        }else{
            val id: Int = intent.getIntExtra("examId",-1)
            binding.InsertBTN.text = "Actualizar"
            binding.deleteBtn.visibility = View.VISIBLE
            val exam = db.getExamen(id)
            binding.tituloPT.setText(exam.titulo)
            binding.fechaPT.setText(transformDate(exam.fecha))
            binding.notaPT.setText(exam.nota.toString())
            binding.asignaturaPT.setSelection(exam.asignatura.ordinal)

            binding.deleteBtn.setOnClickListener{
                db.deleteData(exam)
                onBackPressed()
            }
            binding.InsertBTN.setOnClickListener{
                if (isDataValid()) {
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val exam = Examen(
                        titulo = binding.tituloPT.text.toString(),
                        fecha = dateFormat.parse(binding.fechaPT.text.toString())!!,
                        nota = binding.notaPT.text.toString().toInt(),
                        asignatura = Asignatura.getConstantByName(binding.asignaturaPT.selectedItem.toString()),
                        id = id
                    )
                    db.updateData(exam)
                    onBackPressed()
                }
            }
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }


    private fun onDateSelected(day:Int, month:Int, year:Int){
        var monthFixed=month+1
        binding.fechaPT.setText("$day/$monthFixed/$year")
    }

    private fun isDataValid(): Boolean {
        return !(binding.tituloPT.text.isNullOrBlank() || binding.fechaPT.text.isNullOrBlank()
                || binding.notaPT.text.isNullOrBlank() || binding.asignaturaPT.selectedItem == null)
    }

    private fun transformDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

}