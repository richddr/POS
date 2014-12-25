<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 12/5/2014
  Time: 4:30 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Ver Compra: ${carro.transaccion}</title>
</head>

<body>

    <table class="lista-items-compra">
        <tr>
            <th>Nombre</th>
            <th>Precio Unidad</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
        </tr>
        <g:each in="${items}" var="i">
            <tr>
                <td>${i.producto.nombre}</td>
                <td>${i.producto.precioVenta}</td>
                <td>${i.quantity}</td>
                <td>${i.producto.precioVenta*i.quantity}</td>
            </tr>
        </g:each>
    </table>

    Total: ${totalCompra}
</body>
</html>