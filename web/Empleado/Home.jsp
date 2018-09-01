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
                <div><br><br>
                    <a href="${pageContext.request.contextPath}/empleados.do?operacion=canje">
                    <div class="col-sm-6 col-md-4 tareas" >
                        <div class="thumbnail">
                            <img class="img-responsive imagen" style="max-height: 200px; min-height: 200px;" src="${pageContext.request.contextPath}/images/portfolio/gestionEmpleado.jpg" id="canjear">
                            <div class="caption">
                                <h3><p style="text-decoration: none; color:#c2185b;">Canjear cupón</p></h3>
                                <p>Canjea los cupones de los clientes.</p>
                            </div>
                        </div>
                    </div></a>
                    <a href="${pageContext.request.contextPath}/empleados.do?operacion=updateC">        
                    <div class="col-sm-6 col-md-4" id="cambiarC">
                        <div class="thumbnail">
                            <img class="img-responsive" style="max-height: 200px; min-height: 200px;" src="${pageContext.request.contextPath}/images/portfolio/contra.jpg" alt="">
                            <div class="caption">
                                <h3>Cambio de contraseña</h3>
                                <p>Cambia tu contraseña cuando quieras.</p>
                            </div>
                        </div>
                    </div></a>
                </div>
            </div>
        </section>
    </body>
</html>
