package es.iesfranciscodelosrios.calificaciones.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.iesfranciscodelosrios.calificaciones.model.Examen
import es.iesfranciscodelosrios.calificaciones.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ExamenAdapter(private val examenList: ArrayList<Examen>, private val onItemClicked: (Examen) -> Unit) :RecyclerView.Adapter<ExamenAdapter.ViewHolder>(){
class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val asignatura: TextView = view.findViewById(R.id.asignaturaTV)
        val nota: TextView = view.findViewById(R.id.notaTV)
        val fecha: TextView = view.findViewById(R.id.fechaTV)
        val titulo: TextView = view.findViewById(R.id.tituloTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exam_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        runCatching {
            holder.asignatura.text ="Asignatura: " +  examenList[position].asignatura.toString()
            holder.nota.text ="Nota: " + examenList[position].nota.toString()
            holder.fecha.text ="Fecha: " +  transformDate(examenList[position].fecha)
            holder.titulo.text = examenList[position].titulo
            holder.itemView.setOnClickListener {
                onItemClicked.invoke(examenList[position])
            }

        }.onFailure {
            Log.e("ExamenAdapter", it.message.toString())
        }
    }

    override fun getItemCount(): Int = examenList.size
    private fun transformDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }


}