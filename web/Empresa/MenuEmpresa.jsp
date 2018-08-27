<jsp:include page="../head.jsp" />
<header id="header">
    <nav id="main-nav" class="navbar navbar-default navbar-fixed-top" role="banner">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <a href="../../../../../../AppData/Local/Temp/Rar$DRa11568.28792/pro-fitness-center-free-web-template/index.html"></a>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="logo"></a>
            </div>

            <div class="collapse navbar-collapse navbar-right">
                <ul class="nav navbar-nav dropdown">
                    <li class="scroll active"><a href="#home">Ofertas</a></li>  
                    <li class="scroll"><a href="#services">Empleados</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Usuario <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/usuarios.do?operacion=uCe">Cambiar contraseña</a></li>
                            <li><a>Cerrar sesión</a></li>
                        </ul>
                    </li>                       
                </ul>
            </div>
        </div>
    </nav>
</header>