package edu.pucmm.pwa.proyectofinal.domains

class Producto {
    String nombre
    String descripcion
    float precioVenta
    int cantidadExistencia

    static hasMany = [imagenes:Imagen]
    static constraints = {
        nombre nullable: false, blank: false
        descripcion nullable: true, blank: true
        precioVenta min: 0F
        cantidadExistencia min: 0
    }
}
