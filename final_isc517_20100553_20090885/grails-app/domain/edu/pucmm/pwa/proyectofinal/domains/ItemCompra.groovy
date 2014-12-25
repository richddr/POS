package edu.pucmm.pwa.proyectofinal.domains

class ItemCompra {
    Producto producto
    int quantity
    static belongsTo = [carro:CarroCompra]

    static constraints = {
        carro nullable: true
    }
}
