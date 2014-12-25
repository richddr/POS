<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 12/1/2014
  Time: 4:15 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Portada</title>
</head>

<body>
    <g:if test="${!productos}">
        No hay productos ahora.
    </g:if>
    <g:elseif test="${productos}">
        <g:each in="${productos}" var="p">
            <g:if test="${p.imagenes}">
                <p><g:img dir="resources/products" file="${p.imagenes[0].rutaImagen}"/></p>
            </g:if>
            <g:else>
                <p><g:img dir="resources/products" file="noimageavailable.jpg"/></p>
            </g:else>
            <p>${p.nombre}</p>
            <p>${p.precioVenta}</p>
            <p><g:link controller="producto" action="show" id="${p.id}">Ver Producto</g:link></p>
            <p><g:link controller="carro" action="agregarCarrito" id="${p.id}">Agregar a Carro</g:link></p>
        </g:each>
    </g:elseif>
    <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USUARIO_ALMACEN">
        <form name="logout" method="POST" action="${createLink(controller:'index', action: 'simularEntregaCompras') }"> <input type="submit" value="Simular Entrega"></form>
    </sec:ifAnyGranted>
</body>
</html>