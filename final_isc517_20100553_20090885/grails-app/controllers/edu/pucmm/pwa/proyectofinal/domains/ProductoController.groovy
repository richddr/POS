package edu.pucmm.pwa.proyectofinal.domains

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProductoController {
    def springSecurityService
    def hashService
    static allowedMethods = [save: "POST", update: "PUT,POST", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Producto.list(params), model:[productoInstanceCount: Producto.count()]
    }

    def show(Producto productoInstance) {
        respond productoInstance
    }

    def create() {
        respond new Producto(params)
    }

    @Transactional
    def save(Producto productoInstance) {
        if (productoInstance == null) {
            notFound()
            return
        }

        if (productoInstance.hasErrors()) {
            respond productoInstance.errors, view:'create'
            return
        }
        //verificamos si se le adjunto una imagen
        def rutaImagen = upload()

        //productoInstance.save flush:true, failOnError: true
        if(rutaImagen){
//            println("rutaimagen:" + rutaImagen)
            Imagen img = new Imagen(rutaImagen: rutaImagen).save(flush: true, failOnError: true)
            productoInstance.addToImagenes(img)
        }
        productoInstance.save flush:true, failOnError: true
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'producto.label', default: 'Producto'), productoInstance.id])
                redirect productoInstance
            }
            '*' { respond productoInstance, [status: CREATED] }
        }
    }

    /*Funcion creada para Enviar Archivos al servidor, es este caso: La imagen del cooupon*/
    @Secured(['ROLE_ADMIN', 'ROLE_COMPANY'])
    def upload() {
        String fn = null;
        def f = request.getFile('imagenProducto')
        //def fi = params.get('imagenProducto')
        if (f.empty || !f) {
            flash.message = 'file cannot be empty'
            return null
        }
        else
        {
            fn = f.getOriginalFilename()

//            println("original file name:" + fn)
            String basePath = request.getSession().getServletContext().getRealPath("/")
            String new_name =  hashService.generateMD5(fn) + "." + fn.split("\\.")[1];
            String urlPath = "/resources/products/" + new_name
            String dir = basePath  + urlPath
//            print("URL Imagen:"+urlPath)
            f.transferTo(new File(dir))
            return new_name
        }
    }

    def edit(Producto productoInstance) {
        respond productoInstance
    }

    @Transactional
    def update(Producto productoInstance) {
        if (productoInstance == null) {
            notFound()
            return
        }

        if (productoInstance.hasErrors()) {
            respond productoInstance.errors, view:'edit'
            return
        }
        //verificamos si se le adjunto una imagen nueva
        def rutaImagen = upload()
        if(rutaImagen){
            Imagen img = new Imagen(rutaImagen: rutaImagen).save(flush: true, failOnError: true)
            productoInstance.imagenes.add(img)
        }
        productoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Producto.label', default: 'Producto'), productoInstance.id])
                redirect productoInstance
            }
            '*'{ respond productoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Producto productoInstance) {

        if (productoInstance == null) {
            notFound()
            return
        }

        productoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Producto.label', default: 'Producto'), productoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
