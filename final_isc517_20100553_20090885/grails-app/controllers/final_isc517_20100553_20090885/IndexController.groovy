package final_isc517_20100553_20090885

import edu.pucmm.pwa.proyectofinal.domains.CarroCompra
import edu.pucmm.pwa.proyectofinal.domains.ItemCompra
import edu.pucmm.pwa.proyectofinal.domains.Producto
import edu.pucmm.pwa.proyectofinal.domains.Usuario
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.core.GrantedAuthority

class IndexController {
    def springSecurityService

    def index() {
        //retornamos todos los productos disponibles
        [productos: Producto.findAll()]
    }

    @Secured(['ROLE_ADMIN','ROLE_USUARIO_GENERICO','ROLE_USUARIO_VENTA'])
    def historialVentas(){
        def ventas
        if(grails.plugin.springsecurity.SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN, ROLE_USUARIO_VENTA")){
            ventas = CarroCompra.findAll()
        }
        else if(grails.plugin.springsecurity.SpringSecurityUtils.ifAnyGranted("ROLE_USUARIO_GENERICO")){
            ventas = CarroCompra.findAllByBuyer(springSecurityService.currentUser as Usuario)
        }
        [ventas: ventas]
    }

    def verVenta(){
        CarroCompra carro = CarroCompra.get(params.id)
        def totalCompra = 0
        if(carro){
            if(carro.getItemsCompra().size() > 0){
                carro.getItemsCompra().asList().each {ItemCompra item ->
                    totalCompra += (item.producto.precioVenta * item.quantity)
                }
            }
        }
        [carro : carro, items : carro.getItemsCompra().asList(), totalCompra : totalCompra]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USUARIO_ALMACEN'])
    def verDespachosPendientes(){
        def authorities = springSecurityService.authentication.authorities

        //authorities.eachWithIndex {GrantedAuthority auth -> print("AUTHORITIES DEL USUARIO:"+auth.authority)}
        def despachosPendientes = CarroCompra.findAllWhere(delivered: false)

        [despachosPendientes: despachosPendientes]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USUARIO_ALMACEN'])
    def simularEntregaCompras(){
        //funcion que simula la entrega de la compra al cliente
        //se debe marcar la compra como entregada.
        CarroCompra.findAll().each {CarroCompra compra ->
            compra.delivered = true
            compra.fechaEntrega = new Date()
            compra.save(flush: true, failOnError: true)
        }
        redirect(controller: 'index', action: 'index')
    }
}
