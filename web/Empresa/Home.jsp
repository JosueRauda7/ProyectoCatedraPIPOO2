<%-- 
    Document   : Home
    Created on : Aug 19, 2018, 2:14:18 PM
    Author     : ivanm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Empresa/head.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="/Empresa/MenuEmpresa.jsp"></jsp:include>

        <section style="margin-top: 5%;" >
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="images/portfolio/gestion.png" alt="">
                            <div class="caption">
                                <h3><a  href="${pageContext.request.contextPath}/empresas.do?operacion=listarOferta" style="text-decoration: none; color:#c2185b;">Gestion de ofertas</a></h3>
                            <p>Gestiona las ofertas de tu empresa.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="images/portfolio/gestionEmpleado.jpg" alt="">
                            <div class="caption">
                                <h3><a href="${pageContext.request.contextPath}/empresas.do?operacion=listarEmpleado" style="text-decoration: none; color:#c2185b;"> Gestion de empleados</a></h3>
                                <p>Ten un control de los empleados de tu empresa.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="images/portfolio/contra.jpg" alt="">
                            <div class="caption">
                                <h3><a href="${pageContext.request.contextPath}/empresas.do?operacion=updateC" style="text-decoration: none; color:#c2185b;">Cambio de contraseña</a></h3>
                                <p>Cambia tu contraseña cuando quieras.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
