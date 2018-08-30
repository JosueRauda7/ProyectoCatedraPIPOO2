<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Empleado/head.jsp"></jsp:include>
            <title>Bienvenido</title>
        </head>
        <body>
        <jsp:include page="/Empleado/menuEmpleado.jsp" />
        <section style="margin-top: 5%;" >
            <div class="container">
                <div>
                    <div class="col-sm-6 col-md-4" >
                        <div class="thumbnail">
                            <img class="img-responsive imagen" src="${pageContext.request.contextPath}/images/portfolio/gestionEmpleado.jpg" id="canjear">
                            <div class="caption">
                                <h3><a href="${pageContext.request.contextPath}/empresas.do?operacion=listarEmpleado" style="text-decoration: none; color:#c2185b;">Canjear cupón</a></h3>
                                <p>Canjea los cupones de los clientes.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4" id="cambiarC">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${pageContext.request.contextPath}/images/portfolio/contra.jpg" alt="">
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
