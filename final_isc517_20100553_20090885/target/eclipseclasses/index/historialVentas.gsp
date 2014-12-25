<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 12/1/2014
  Time: 5:20 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Historial Ventas</title>
</head>

<body>
    <sec:ifLoggedIn>
        <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USUARIO_VENTA, ROLE_USUARIO_GENERICO">
            <g:if test="${ventas}">
                <table>
                    <tr>
                        <th>Compra Id</th>
                        <th>Monto Compra</th>
                        <th>Fecha Compra</th>
                        <th>Fecha Entregada</th>
                        <th>Ver Detalles</th>
                        <th>PDF VF</th>
                        <th>PDF CF</th>
                    </tr>

                    <g:each in="${ventas}" var="compra">
                        <tr>
                            <td>${compra.transaccion}</td>
                            <td>${compra.montoCompra}</td>
                            <td>${compra.fechaCompra}</td>
                            <td>${compra.fechaEntrega}</td>
                            <td><a href="${g.createLink(controller: 'index', action: 'verVenta', id: compra.id)}">Ver</a></td>
                            <td><a href="${g.createLink(controller: 'carro', action: 'descargarFactura', params: [compraId:compra.id, valorFiscal: true] )}">PDF</a></td>
                            <td><a href="${g.createLink(controller: 'carro', action: 'descargarFactura', params: [compraId:compra.id, consumidorFinal: true] )}">PDF</a></td>
                        </tr>
                    </g:each>
                </table>
            </g:if>
            <g:else>
                No se han realizado compras.
            </g:else>
        </sec:ifAnyGranted>
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        Debes loguearte para ver los servicios.
        <g:link controller="login" action="auth">Login</g:link>
    </sec:ifNotLoggedIn>
</body>
</html>