package final_isc517_20100553_20090885

import edu.pucmm.pwa.proyectofinal.domains.CarroCompra
import edu.pucmm.pwa.proyectofinal.domains.Usuario
import grails.transaction.Transactional

@Transactional
class CorreoService {
    def springSecurityService
    def mailService

    def notificarNuevoUsuario(Usuario u){
        mailService.sendMail {
            to u.email
            from "final@pwa.com"
            subject "Proyecto Final WEB: Store Credentials"
            body "Bienvenido a TuTienda!\n Saludos ${u.nombre}!,\n\n" +
                 "Tus credenciales para acceder a nuestra portada es:\n" +
                 "Usuario:${u.username}\nContraseña:${u.password}"
        }
        println("Se envio un correo para notificar un nuevo usuario...")
    }

    def notificarUsuarioDeptAlmacen(Usuario u, CarroCompra compra, String reporteDespachoName, String reporteDespachoFilePath){
        mailService.sendMail {
            multipart true
            to u.email
            from "final@pwa.com"
            subject "Proyecto Final WEB: Almacen: Orden aa Entregar"
            body "Saludos ${u.nombre}\n\nLa compra #${compra.transaccion} fue completada satisfactoriamente." +
                  "Se debe de apartar dichos productos y enviarlos al cliente."
            attachBytes reporteDespachoName, "application/pdf", new File(reporteDespachoFilePath).getBytes()
            //To get started quickly, try the following
            //attachBytes './web-app/images/grails_logo.jpg','image/jpg', new File('./web-app/images/grails_logo.jpg').readBytes()
        }
        println("Se envio un correo a un usuario del dept de almacen...")
    }
}
