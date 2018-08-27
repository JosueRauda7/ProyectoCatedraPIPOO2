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
                    <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="logo"></a>
                </div>
				
                <div class="collapse navbar-collapse navbar-right">
                    <ul class="nav navbar-nav">
                       
                        <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Empresas <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=nuevo">Agregar empresas</a></li>
                        <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=listar">Ver empresas</a></li>
                    </ul>
                </li>
		        
                        <li class="scroll"><a href="${pageContext.request.contextPath}/rubros.do?operacion=listar">Ver Rubros</a></li>
                        
                        <li class="scroll"><a href="#pricing">Ver clientes</a></li>
                            
                        <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Administrador <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=upCa">Cambiar contraseña</a></li>
                        <li><a href="${pageContext.request.contextPath}/empresas.do?operacion=listar">Cerrar session</a></li>
                    </ul>
                </li>                      
                    </ul>
                </div>
            </div><!--/.container-->
        </nav><!--/nav-->
    </header><!--/header-->