<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 12/1/2014
  Time: 5:21 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Despachos Pendientes</title>
</head>

<body>
    <sec:ifLoggedIn>
        <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USUARIO_ALMACEN">
            <g:if test="${despachosPendientes}">
                <table>
                    <tr>
                        <th>Despacho Id</th>
                        <th>Monto Despacho</th>
                        <th>Fecha Compra</th>
                        <th>Ver Detalles</th>
                        <th>PDF VF</th>
                        <th>PDF CF</th>
                    </tr>

                    <g:each in="${despachosPendientes}" var="compra">
                        <tr>
                            <td>${compra.transaccion}</td>
                            <td>${compra.montoCompra}</td>
                            <td>${compra.fechaCompra}</td>
                            <td><a href="${g.createLink(controller: 'index', action: 'verVenta', id: compra.id)}">Ver</a></td>
                            <td><a href="${g.createLink(controller: 'carro', action: 'descargarFactura', params: [compraId:compra.id, valorFiscal: true] )}">PDF</a></td>
                            <td><a href="${g.createLink(controller: 'carro', action: 'descargarFactura', params: [compraId:compra.id, consumidorFinal: true] )}">PDF</a></td>
                        </tr>
                    </g:each>
                </table>
            </g:if>
            <g:else>
                No tiene despachos pendientes.
            </g:else>
        </sec:ifAnyGranted>
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        Debes loguearte para ver los servicios.
        <g:link controller="login" action="auth">Login</g:link>
    </sec:ifNotLoggedIn>
</body>
</html>