package edu.pucmm.pwa.proyectofinal.domains

class Usuario {

	transient springSecurityService

	String username
	String password
    String nombre
    String apellido
    String email
    String rnc

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
        email blank: false, nullable: false
        nombre blank: false, nullable: false
        apellido blank: false, nullable: false
        rnc nullable: true, blank: true
	}

    static hasMany = [compras:CarroCompra]

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
