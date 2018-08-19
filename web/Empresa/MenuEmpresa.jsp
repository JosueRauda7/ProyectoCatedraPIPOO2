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
                
            </div>
            <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
               <li> <a href="Login.jsp" style="text-decoration: none; color:white;">CUPONERA UDB</a></li> 
              <li class="active"><a href="${pageContext.request.contextPath}/Login.jsp">Cerrar sessión</a></li> 
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false">Gestion de ofertas <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/autores.do?op=nuevo">Registrar autor</a></li>
                <li><a href="${pageContext.request.contextPath}/autores.do?op=listar">Ver lista de autores</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false">Gestion de empleados<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/generos.do?op=nuevo">Registrar genero</a></li>
                <li><a href="${pageContext.request.contextPath}/generos.do?op=listar">Ver lista de generos</a></li>
              </ul>
            </li>
          </ul>
          
        </div>
        </div><!--/.container-->
    </nav><!--/nav-->
</header><!--/header-->