<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 12/4/2014
  Time: 8:56 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Compra Realizada!</title>
</head>

<body>
    <h1>Gracias por su compra</h1>
    <p>Su Transacción #${compra.transaccion} será entregada a la brevedad posible a la siguiente direccion:</p>
    <g:form action="descargarFactura">
        <g:hiddenField name="compraId" value="${compra.id}"/>
        <g:hiddenField name="valorFiscal" value="true"/>
        <g:submitButton name="factura" value="Descargar Factura Valor Fiscal"/>
    </g:form>
    <g:form action="descargarFactura">
        <g:hiddenField name="compraId" value="${compra.id}"/>
        <g:hiddenField name="consumidorFinal" value="true"/>
        <g:submitButton name="factura" value="Descargar Factura Consumidor Final"/>
    </g:form>
    <g:form action="descargarFactura">
        <g:hiddenField name="compraId" value="${compra.id}"/>
        <g:submitButton name="factura" value="Descargar Factura Normal"/>
    </g:form>
</body>
</html>