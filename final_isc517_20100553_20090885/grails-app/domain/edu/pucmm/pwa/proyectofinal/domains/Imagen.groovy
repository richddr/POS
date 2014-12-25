package edu.pucmm.pwa.proyectofinal.domains

class Imagen {
    String rutaImagen

    static constraints = {
        rutaImagen nullable: false, blank: false
    }
}
