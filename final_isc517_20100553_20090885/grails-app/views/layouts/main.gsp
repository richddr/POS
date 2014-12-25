<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->

<head>
   <!-- <link rel="stylesheet" href="/css/common-style.css" type="text/css" />-->
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='style.css'/>" type="text/css" />
    <!--[if IE 6]><link rel="stylesheet" href="<g:createLinkTo dir="css" file="ie_styles.css" />" type="text/css"/><![endif]-->

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Practica Final - PWA 2014"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <g:layoutHead/>
</head>

<body>
<!-- top lines for style -->
<div id="top_green"></div>
<div id="top_dark"></div>

<div id="wrapper">

    <div id="header">
        <!-- logo - editable via logo.png -->
        <div id="logo"><h3>Practica Final<br/>
            Programación Web Avanzada<br/>
            20090885 y 20100553 <br/>
        </h3>
        </div>

        <div id="user_links">
            <sec:ifLoggedIn>
                Bienvenid@ <sec:username/>! &nbsp;
                <form name="logout" method="POST" action="${createLink(controller:'logout') }"> <input type="submit" value="logout"></form>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                Debes loguearte para ver los servicios.
                %{--<g:link controller="login" action="auth">Login</g:link>--}%
            </sec:ifNotLoggedIn>
        </div>
    </div>

    <!-- edit navigation items here -->
    <div id="nav">
        <ul>
            <li><g:link controller="index">Portada</g:link></li>
            <sec:ifLoggedIn>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <li><g:link controller="usuario" action="create">Crear Usuario</g:link></li>
                    <li><g:link controller="producto" action="create">Crear Producto</g:link></li>
                </sec:ifAnyGranted>

                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USUARIO_GENERICO, ROLE_USUARIO_VENTA">
                    <li><g:link controller="producto" action="index">Ver Productos</g:link></li>
                    <li><g:link controller="index" action="historialVentas">Historial Ventas</g:link></li>
                </sec:ifAnyGranted>

                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USUARIO_ALMACEN">
                    <li><g:link controller="index" action="verDespachosPendientes">Despachos Pendientes</g:link></li>
                    <li><g:link controller="graficos" action="despachosPendientes">Grafico Despachos Pendientes</g:link></li>
                    <li><g:link controller="graficos" action="despachosRealizadosHoy">Grafico Despachos Realizados Dia</g:link></li>
                    <li><g:link controller="graficos" action="totalCompras">Grafico Compras</g:link></li>
                </sec:ifAnyGranted>

                <sec:ifAnyGranted roles="ROLE_USUARIO_GENERICO">
                    <li><g:link controller="carro" action="index">Carro Compra</g:link></li>
                </sec:ifAnyGranted>
            </sec:ifLoggedIn>

        </ul>
    </div>

    <div id="content">
        <!-- edit sub navigation and quick links here -->

        <div id="left">

            <g:layoutBody/>
        </div>

        <!-- edit sidebar items here --></div>

    <!-- edit footer items here -->
    <div id="footer">
        <div id="copyright">Copyright &copy; 2014 David De La Hoz &amp; Richard Garcia, PUCMM, Programación Web Avanzada</div>


    </div>


</div>

</body>
</html>
