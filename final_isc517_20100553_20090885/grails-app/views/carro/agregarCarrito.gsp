<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 12/4/2014
  Time: 7:15 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Agregar ${producto.nombre} a carro</title>
</head>

<body>
    <g:if test="${producto.imagenes}">
        <p><g:img dir="resources/products" file="${producto.imagenes[0].rutaImagen}"/></p>
    </g:if>
    <g:else>
        <p><g:img dir="resources/products" file="noimageavailable.jpg"/></p>
    </g:else>
    <p>${producto.nombre}</p>
    <p>${producto.precioVenta}</p>
    <g:form action="agregar">
        <fieldset class="form">
            <g:textField name="cantidad" value="1" required="true"/>
            <g:hiddenField name="productoId" value="${producto.id}"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="agregar" class="carro" value="Agregar" />
        </fieldset>
    </g:form>
</body>
</html>