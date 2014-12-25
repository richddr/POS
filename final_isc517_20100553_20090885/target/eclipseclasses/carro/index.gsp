<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 12/2/2014
  Time: 4:54 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Mi Carro</title>
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
    <g:form url="https://www.sandbox.paypal.com/cgi-bin/webscr/" action="" method="post">
        <fieldset class="form">
            <input type="hidden" name="cbt" value="Completar proceso de Compra"> %{--  --}%
            <input type="hidden" name="rm" value="2"> %{--Indicando que haga un redirect por el metodo POST--}%
            <input type="hidden" name="return" value="${g.createLink(controller: 'carro', action: 'procesarPago', absolute: true)}">
            <input type="hidden" name="cancel_return" value="${g.createLink(controller: 'carro', action: 'compraCancelada', absolute: true)}">

            <input type="hidden" name="cmd" value="_cart">
            <input type="hidden" name="upload" value="1">
            <input type="hidden" name="business" value="richagarcia-facilitator@gmail.com">
            <input type="hidden" name="currency_code" value="USD">

            %{--<input type="hidden" name="item_name" value="Prueba de Compra">--}%
            %{--<input type="hidden" name="amount" value="20.00">--}%

            <g:each in="${items}" var="i" status="inum">
                <input type="hidden" name="item_name_${inum+1}" value="${i.producto.nombre}">
                <input type="hidden" name="item_number_${inum+1}" value="${inum+1}">
                <input type="hidden" name="amount_${inum+1}" value="${i.producto.precioVenta}">
                <input type="hidden" name="quantity_${inum+1}" value="${i.quantity}">
            </g:each>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="pagar" class="carro" value="Pagar via Paypal" />
        </fieldset>
    </g:form>
</body>
</html>