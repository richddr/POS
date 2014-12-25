import edu.pucmm.pwa.proyectofinal.domains.Imagen
import edu.pucmm.pwa.proyectofinal.domains.Rol
import edu.pucmm.pwa.proyectofinal.domains.Usuario
import edu.pucmm.pwa.proyectofinal.domains.UsuarioRol

class BootStrap {

    def init = { servletContext ->
        //Creando el rol
        def roleAdmin = new Rol(authority: "ROLE_ADMIN").save(failOnError: true);
        def roleUsuarioGenerico = new Rol(authority: "ROLE_USUARIO_GENERICO").save(failOnError: true);
        def roleUsuarioVenta = new Rol(authority: "ROLE_USUARIO_VENTA").save(failOnError: true);
        def roleUsuarioAlmacen = new Rol(authority: "ROLE_USUARIO_ALMACEN").save(failOnError: true);

        //creando el usuario.
        def admin=new Usuario(username: "admin",
                password: "admin",
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                email: "richagarcia@gmail.com",
                nombre: "Admin",
                apellido: "Admin").save(failOnError: true);
        def usuarioAlmacen=new Usuario(username: "richddr",
                password: "123",
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                email: "richard11_383@msn.com",
                nombre: "Richard-Almacen",
                apellido: "Garcia").save(failOnError: true);
        def usuarioGenerico=new Usuario(username: "dvd",
                password: "123",
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                email: "dvdjoaquin@gmail.com",
                nombre: "David",
                apellido: "De La Hoz").save(failOnError: true);
        def usuarioVenta=new Usuario(username: "user",
                password: "123",
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                email: "richard11_383@msn.com",
                nombre: "UsuarioVenta",
                apellido: "UsuarioVenta").save(failOnError: true);
        //asignando el usuario.
        UsuarioRol.create(admin, roleAdmin, true);
        UsuarioRol.create(usuarioAlmacen, roleUsuarioAlmacen, true);
        UsuarioRol.create(usuarioGenerico, roleUsuarioGenerico, true)
        UsuarioRol.create(usuarioVenta, roleUsuarioVenta, true)

        Imagen noImgDefaultArt = new Imagen(rutaImagen: "/resources/products/noimageavailable.jpg").save(flush: true)
    }
    def destroy = {
    }
}
