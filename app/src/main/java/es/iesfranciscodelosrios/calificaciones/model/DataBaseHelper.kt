package es.iesfranciscodelosrios.calificaciones.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.Date

class DataBaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    //TODO: CAMBIA LOS NOMBRES
    companion object{
        const val DATABASE_NAME = "examens.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "examenes"
        const val COL_ID = "id"
        const val COL_ASIGNATURA = "asignatura"
        const val COL_NOTA = "nota"
        const val COL_FECHA = "fecha"
        const val COL_TITULO = "titulo"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_ASIGNATURA TEXT," +
                "$COL_TITULO TEXT,$COL_NOTA INTEGER,$COL_FECHA TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addData(examen: Examen){
        val db = this.writableDatabase
        val contentVals = ContentValues()
        contentVals.put(COL_ASIGNATURA, examen.asignatura.name)
        contentVals.put(COL_TITULO, examen.titulo)
        contentVals.put(COL_NOTA, examen.nota)
        contentVals.put(COL_FECHA, examen.fecha.toString())
        db.insert(TABLE_NAME, null, contentVals)
    }

    fun updateData(examen: Examen){
        val db = this.writableDatabase
        val contentVals = ContentValues()
        contentVals.put(COL_ASIGNATURA, examen.asignatura.name)
        contentVals.put(COL_TITULO, examen.titulo)
        contentVals.put(COL_NOTA, examen.nota)
        contentVals.put(COL_FECHA, examen.fecha.toString())
        db.update(TABLE_NAME, contentVals, "$COL_ID=?", arrayOf(examen.id.toString()))
    }

    fun deleteData(examen: Examen){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(examen.id.toString()))
    }

    @SuppressLint("Range")
    fun getExamen(id: Int): Examen {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_ID=?", arrayOf(id.toString()))
        cursor.moveToFirst()
        val asignatura = Asignatura.valueOf(cursor.getString(cursor.getColumnIndex(COL_ASIGNATURA)))
        val nota = cursor.getInt(cursor.getColumnIndex(COL_NOTA))
        val fecha = cursor.getString(cursor.getColumnIndex(COL_FECHA))
        val titulo = cursor.getString(cursor.getColumnIndex(COL_TITULO))
        return Examen(asignatura, nota, Date.valueOf(fecha), titulo, id)
    }
    @SuppressLint("Range")
    fun getAllExamenes(): ArrayList<Examen>{
        val examenes = ArrayList<Examen>()
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                val asignatura = Asignatura.valueOf(cursor.getString(cursor.getColumnIndex(COL_ASIGNATURA)))
                val nota = cursor.getInt(cursor.getColumnIndex(COL_NOTA))
                val fecha = cursor.getString(cursor.getColumnIndex(COL_FECHA))
                val titulo = cursor.getString(cursor.getColumnIndex(COL_TITULO))
                examenes.add(Examen(asignatura, nota, Date.valueOf(fecha), titulo, id))
            }while(cursor.moveToNext())
        }
        return examenes
    }
}