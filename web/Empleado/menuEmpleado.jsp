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
                <h1> <a href="${pageContext.request.contextPath}/empleados.do?operacion=home" class="navbar-brand" style="text-decoration: none; color:white;">CUPONERA UDB</a></h1>
            </div>

            <div class="collapse navbar-collapse navbar-right">
                <ul class="nav navbar-nav">
                    <li class="scroll"><a href="${pageContext.request.contextPath}/empleados.do?operacion=home">Home</a></li>  
                    <li class="scroll"><a href="${pageContext.request.contextPath}/empleados.do?operacion=canje">Canjear cup�n</a></li>
                    <li></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Usuario <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/empleados.do?operacion=updateC">Cambiar contrase�a</a></li>
                            <li><a href="${pageContext.request.contextPath}/empleados.do?operacion=cerrar">Cerrar sesi�n</a></li>
                        </ul>
                    </li>                    
                </ul>
            </div>
        </div>
    </nav>
</header>