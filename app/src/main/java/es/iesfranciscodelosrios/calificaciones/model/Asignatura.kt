package es.iesfranciscodelosrios.calificaciones.model

//TODO: Yo he creado este enum pal campo desplegable que pide, tu crea otro pichita
enum class Asignatura(val nombre: String) {
    MATEMATICAS("Matemáticas"),
    LENGUA("Lengua"),
    HISTORIA("Historia"),
    GEOGRAFIA("Geografía"),
    INFORMATICA("Informática"),
    FISICA("Física"),
    QUIMICA("Química"),
    BIOLOGIA("Biología"),
    RELIGION("Religión"),
    EDUCACION_FISICA("Educación Física"),
    MUSICA("Música"),
    ARTES("Artes"),
    ECONOMIA("Economía"),
    EMPRESA("Empresa");

    override fun toString(): String {
        return nombre
    }

    companion object{
        fun getConstantByName(name: String): Asignatura {
            return Asignatura.values().first { it.nombre == name }
        }
    }
}