<%-- 
    Document   : Home
    Created on : Aug 19, 2018, 2:14:18 PM
    Author     : ivanm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="webthemez">
        <title>Cuponera - Login  ${sessionScope.correo}</title>
        <!-- core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/animate.min.css" rel="stylesheet"> 
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <jsp:include page="/Empresa/MenuEmpresa.jsp"></jsp:include>

        <section style="margin-top: 5%;" >
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="images/portfolio/01.jpg" alt="">
                            <div class="caption">
                                <h3><a  href="${pageContext.request.contextPath}/ofertas.do?operacion=listar" style="text-decoration: none; color:#c2185b;">Gestion de ofertas</a></h3>
                            <p>Propón nuevas ofertas en cupones.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="images/portfolio/02.jpg" alt="">
                            <div class="caption">
                                <h3><a href="${pageContext.request.contextPath}/empleados.do?operacion=nuevo" style="text-decoration: none; color:#c2185b;"> Gestion de empleados</a></h3>
                                <p>Ten un control de los empleados de tu empresa.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="images/portfolio/03.jpg" alt="">
                            <div class="caption">
                                <h3>Cambio de contraseña</h3>
                                <p>Cambia tu contraseña cuando quieras.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
