
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
                <a class="navbar-brand" href="${pageContext.request.contextPath}/empresas.do?operacion=home"><img src="images/logo.png" alt="logo"></a>
            </div>

            <div class="collapse navbar-collapse navbar-right">

                <ul class="nav navbar-nav">

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            Ofertas<b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=nuevaOferta">Nueva oferta</a></li>
                            <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=listarOferta">Ver ofertas</a></li>
                        </ul>
                    </li>
                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            Empleados<b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=nuevoEmpleado">Nuevo empleado</a></li>
                            <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=listarEmpleado">Ver empleados</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            Empresa<b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=updateC">Cambiar contraseña</a></li>
                            <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=cerrar">Cerrar Sesión</a></li>
                        </ul>
                    </li>                      

            </div>
        </div><!--/.container-->
    </nav><!--/nav-->
</header><!--/header-->