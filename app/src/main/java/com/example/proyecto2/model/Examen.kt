package com.example.proyecto2.model

import java.util.*

data class Examen(val asignatura: Asignatura, val nota: Int, val fecha: Date,val titulo: String, var id: Int? = null)
