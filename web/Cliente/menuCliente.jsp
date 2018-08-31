<jsp:include page="../head.jsp" />
<header id="header">
    <nav id="main-nav" class="navbar navbar-default navbar-fixed-top" role="banner">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="logo">Cuponera</a> --> 
                <h1> <a href="${pageContext.request.contextPath}/clientes.do?operacion=inicio" class="navbar-brand" style="text-decoration: none; color:white;">CUPONERA UDB</a></h1>
            </div>
            <div class="collapse navbar-collapse navbar-right">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/clientes.do?operacion=inicio">Inicio</a></li>  
                    <li><a href="${pageContext.request.contextPath}/clientes.do?operacion=listar">Comprar Cupones</a></li>
                    <li><a href="${pageContext.request.contextPath}/clientes.do?operacion=misCupones">Mis Cupones</a></li>

                    <li><a href="${pageContext.request.contextPath}/clientes.do?operacion=ver">Carrito de Compras</a></li>
                    <li><a href="${pageContext.request.contextPath}/usuarios.do?operacion=cerrar">Cerrar Sesión</a></li>

                    <li><a href="${pageContext.request.contextPath}/clientes.do?operacion=ver">Carrito de Compras</a></li>                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Usuario <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/clientes.do?operacion=updateC">Cambiar contraseña</a></li>
                            <li><a href="${pageContext.request.contextPath}/clientes.do?operacion=cerrar">Cerrar Sesión</a></li>
                        </ul>
                    </li> 

                </ul>
            </div>
        </div><!--/.container-->
    </nav><!--/nav-->
</header><!--/header-->
