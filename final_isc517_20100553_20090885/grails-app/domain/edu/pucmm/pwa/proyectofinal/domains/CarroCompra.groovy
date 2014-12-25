package edu.pucmm.pwa.proyectofinal.domains

class CarroCompra {
    Date fechaCompra
    Date fechaEntrega
    boolean delivered

    static hasMany = [itemsCompra: ItemCompra]
    static hasOne = [buyer:Usuario]

    String nombre;
    String factura;
    String transaccion;
    String estatusPago

    Float montoCompra;
    Float montoFee;
    Float montoEnvio;
    Float montoImpuesto;
    Float montoManejo;

    String compradorId;
    String emailComprador;
    String vendedor;

    String direccion;
    String zip;
    String estado;
    String ciudad;

    static  mapping = {
        itemsCompra lazy: false
    }

    static constraints = {
        fechaEntrega nullable: true
        direccion nullable: true
        zip nullable: true
        estado nullable: true
        ciudad nullable: true
        vendedor nullable: true
        emailComprador nullable: true
        compradorId nullable: true
        montoCompra nullable: true
        montoFee nullable: true
        montoEnvio nullable: true
        montoImpuesto nullable: true
        montoManejo nullable: true
        transaccion nullable: true
        estatusPago nullable: true
        nombre nullable: true
        factura nullable: true

    }
}
