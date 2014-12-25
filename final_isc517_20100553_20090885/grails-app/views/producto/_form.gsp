<%@ page import="edu.pucmm.pwa.proyectofinal.domains.Producto" %>



<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="producto.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${productoInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="producto.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${productoInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'precioVenta', 'error')} required">
	<label for="precioVenta">
		<g:message code="producto.precioVenta.label" default="Precio Venta" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="precioVenta" value="${fieldValue(bean: productoInstance, field: 'precioVenta')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'cantidadExistencia', 'error')} required">
	<label for="cantidadExistencia">
		<g:message code="producto.cantidadExistencia.label" default="Cantidad Existencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cantidadExistencia" type="number" min="0" value="${productoInstance.cantidadExistencia}" required=""/>

</div>

%{--<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'imagenes', 'error')} ">--}%
	%{--<label for="imagenes">--}%
		%{--<g:message code="producto.imagenes.label" default="Imagenes" />--}%
		%{----}%
	%{--</label>--}%
	%{--<g:select name="imagenes" from="${edu.pucmm.pwa.proyectofinal.domains.Imagen.list()}" multiple="multiple" optionKey="id" size="5" value="${productoInstance?.imagenes*.id}" class="many-to-many"/>--}%

%{--</div>--}%


