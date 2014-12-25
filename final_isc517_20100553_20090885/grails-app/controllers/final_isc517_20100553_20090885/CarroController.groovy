package final_isc517_20100553_20090885

import edu.pucmm.pwa.proyectofinal.domains.CarroCompra
import edu.pucmm.pwa.proyectofinal.domains.ItemCompra
import edu.pucmm.pwa.proyectofinal.domains.Producto
import edu.pucmm.pwa.proyectofinal.domains.Usuario
import edu.pucmm.pwa.proyectofinal.domains.UsuarioRol
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperPrint

@Secured(['ROLE_USUARIO_GENERICO'])
class CarroController {
    def springSecurityService
    def correoService
    def reporteService
    def index() {
        CarroCompra carro_ = request.getSession().getAttribute("CarroCompra") as CarroCompra
       CarroCompra carro
        if(!carro_){
            carro = new CarroCompra(buyer: springSecurityService.currentUser as Usuario,
                    delivered: false,
                    fechaCompra: new Date())
            carro.setItemsCompra(new ArrayList<ItemCompra>().toSet())

        }
        else{
            carro = CarroCompra.get(carro_?.id)
        }
        def totalCompra = 0
        if(carro.getItemsCompra().size() > 0){
            carro.getItemsCompra().toList().each {ItemCompra item ->
                totalCompra += (item.producto.precioVenta * item.quantity)
            }
        }
        [carro : carro, items : carro.getItemsCompra().asList(), totalCompra : totalCompra]
    }

    def compraCancelada(){
        println("Compra cancelada")
        CarroCompra carro_ = request.getSession().getAttribute("CarroCompra") as CarroCompra
        CarroCompra carro = CarroCompra.get(carro_.id)
        carro.delete(flush: true)
        request.getSession().removeAttribute("CarroCompra")
        render params;
    }

    def descargarFactura(){
        CarroCompra compra = CarroCompra.findById(params.compraId as Long)
        //la vaina de JasperReports...
        def nombreReporte = "Reporte Exportado Compra:${compra.transaccion}"
        def tempFilePath = File.createTempFile(nombreReporte, ".pdf").getPath()
        def print
        if(params.valorFiscal){
            print = reporteService.getReporteCompra(compra.id, true, false, request )
        }
        else if(params.consumidorFinal){
            print = reporteService.getReporteCompra(compra.id, false, true, request)
        }
        else{
            print = reporteService.getReporteCompra(compra.id, false, false, request)
        }
        JasperExportManager.exportReportToPdfFile(print, tempFilePath);
        response.setContentType('APPLICATION/PDF')
        response.setHeader("Content-Disposition", "Attachment;Filename=\"${nombreReporte}\"")
        //def file = new File(tempFilePath).bytes
        response.outputStream << new File(tempFilePath).bytes
    }

    @Transactional
    def procesarPago(){
        println("Compra realizada con exito.")
        println("Parametros recibidos: "+params)

        //Recibiendo los valores de Paypal.
        CarroCompra compra_ = request.getSession().getAttribute("CarroCompra") as CarroCompra
        CarroCompra compra = CarroCompra.lock(compra_.id)
        if(!compra){
            compra = new CarroCompra(buyer: springSecurityService.currentUser as Usuario,
                    delivered: false,
                    fechaCompra: new Date())
        }
        //compra.factura=params.invoice;
        //compra.buyer = springSecurityService.currentUser as Usuario
        compra.transaccion=params.txn_id;
        compra.nombre=params.item_name;
        compra.estatusPago=params.payment_status;

        compra.montoCompra= params.payment_gross as Float;
        compra.montoImpuesto= params.tax as Float
        compra.montoManejo= params.handling_amount as Float
        compra.montoFee= params.payment_fee as Float
        compra.montoEnvio= params.shipping as Float

        compra.compradorId=params.payer_id;
        compra.emailComprador=params.payer_email;
        compra.fechaCompra=new Date();
        compra.vendedor=params.business;

        compra.ciudad=params.address_city;
        compra.zip=params.address_zip;
        compra.estado=params.address_state;
        compra.direccion=params.address_name;

        compra.merge()
        compra.save(flush: true, failOnError: true);
        notificarDeptAlmacen(compra)
        request.getSession().removeAttribute("CarroCompra")
        refreshInventory(compra)
        redirect(action: 'compraRealizada', id: compra.id);
    }
    def refreshInventory(CarroCompra compra){
        compra.getItemsCompra().asList().each {ItemCompra p ->
            Producto prod = Producto.get(p.producto.id)
            if(prod){
                prod.cantidadExistencia -= p.quantity
                prod.save(flush: true)
            }
        }
    }

    def notificarDeptAlmacen(CarroCompra compra){
        //se le envia un correo a todos los usuario del dept de almacen
        def nombreReporte = "Reporte Exportado Despacho:${compra.transaccion}"
        def tempFilePath = File.createTempFile(nombreReporte, ".pdf").getPath()
        JasperPrint reporte = reporteService.getReporteDespacho(compra.id, false, false, request)
        JasperExportManager.exportReportToPdfFile(reporte, tempFilePath);
        UsuarioRol.findAll().each {UsuarioRol ur ->
            if(ur.rol.authority == "ROLE_USUARIO_ALMACEN"){
                correoService.notificarUsuarioDeptAlmacen(ur.usuario, compra, nombreReporte+".pdf", tempFilePath)
            }
        }
    }

    def compraRealizada(){
        def compra = CarroCompra.lock(params.id);

        [compra : compra]
    }

    def agregarCarrito(){
        Producto p = Producto.findById(params.id as Long)
        if(p){
            [producto : p]
        }
        else{
            respond view: 'index'
        }
    }

    def agregar(){
        def cantidad = params.cantidad
        Producto p = Producto.findById(params.productoId as Long)

        if(p && cantidad){
            CarroCompra carro_ = request.getSession().getAttribute("CarroCompra") as CarroCompra
            CarroCompra carro
            if(carro_){
                carro = CarroCompra.lock(carro_?.id)
                carro.addToItemsCompra((new ItemCompra(producto: p, quantity: cantidad).save(flush: true, failOnError: true)))
            }
            else{
                carro = new CarroCompra(buyer: springSecurityService.currentUser as Usuario,
                        delivered: false,
                        fechaCompra: new Date()).save(flush: true, failOnError: true)
                def items = carro.getItemsCompra()
                if(items){
                    carro.addToItemsCompra((new ItemCompra(producto: p, quantity: cantidad).save(flush: true, failOnError: true)))
                }
                else{
                    carro.setItemsCompra(new ArrayList<ItemCompra>().toSet())
                    carro.addToItemsCompra((new ItemCompra(producto: p, quantity: cantidad).save(flush: true, failOnError: true)))
                }
            }
            carro.merge()
            carro.save(flush: true, failOnError: true)
            request.getSession().setAttribute("CarroCompra", carro)
            redirect controller: 'carro', action: 'index'
        }
        else{
            redirect( controller: 'index', action:'index')
        }
    }
}
