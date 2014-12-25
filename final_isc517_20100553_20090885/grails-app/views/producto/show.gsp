
<%@ page import="edu.pucmm.pwa.proyectofinal.domains.Producto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-producto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-producto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list producto">
			
				<g:if test="${productoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="producto.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${productoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="producto.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${productoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.precioVenta}">
				<li class="fieldcontain">
					<span id="precioVenta-label" class="property-label"><g:message code="producto.precioVenta.label" default="Precio Venta" /></span>
					
						<span class="property-value" aria-labelledby="precioVenta-label"><g:fieldValue bean="${productoInstance}" field="precioVenta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.cantidadExistencia}">
				<li class="fieldcontain">
					<span id="cantidadExistencia-label" class="property-label"><g:message code="producto.cantidadExistencia.label" default="Cantidad Existencia" /></span>
					
						<span class="property-value" aria-labelledby="cantidadExistencia-label"><g:fieldValue bean="${productoInstance}" field="cantidadExistencia"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.imagenes}">
				<li class="fieldcontain">
					<span id="imagenes-label" class="property-label"><g:message code="producto.imagenes.label" default="Imagenes" /></span>

                        <g:if test="${productoInstance.imagenes}">
                            <g:each in="${productoInstance.imagenes}" var="i">
                                <span class="property-value" aria-labelledby="imagenes-label">
									<g:link controller="imagen" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link>
								</span>
								<g:img dir="resources/products" file="${i.rutaImagen}"/>
                            </g:each>
                        </g:if>
						<g:else>
                            <g:img dir="resources/products" file="noimageavailable.jpg"/>
						</g:else>
					
				</li>
				</g:if>

                <li>
                    %{--agregar a carrito--}%
                    <g:link controller="carro" action="agregarCarrito" id="${productoInstance?.id}">Agregar a Carro</g:link>
                </li>
			
			</ol>
			<g:form url="[resource:productoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${productoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
