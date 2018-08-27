<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar contraseña</title>
        <jsp:include page="${pageContext.request.contextPath}/head.jsp" />
    </head>
    <body>
        <jsp:include page="/Cliente/menuCliente.jsp" />
        <div id="formularioCambiarContra">
            <div class="container-fluid">
                <div class="row">
                    <h3 class="h3">Cambiar contraseña</h3>
                </div><br><br>
                <div class="row">
                    <div class="col-md-7">
                        <form role="form" action="${pageContext.request.contextPath}/usuarios.do" method="POST">
                            <div class="form-group">
                                <div>
                                    <input type="hidden" name="tipo" value="4" />
                                    <input type="hidden" name="operacion" value="cambiarC" />
                                    <input type="password" class="form-control contra" name="contrasenaActual" placeholder="Ingrese su contraseña actual"><br>
                                    <input type="password" class="form-control contra" name="confirmarContrasena" placeholder="Vuelva a ingresar su contraseña actual"><br>
                                    <input type="password" class="form-control contraConf" name="nuevaContrasena" placeholder="Ingrese su nueva contraseña"><br><br>
                                    <input type="submit" class="btn btn-info" name="actualizarContra" id="actualizarContra" value="Actualizar contraseña">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            <c:if test="${not empty Exito}">
            alertify.success('${Exito}');
                <c:set var="Exito" value="" scope="session"></c:set>
            </c:if>
            <c:if test="${not empty Fracaso}">
            alertify.error('${Fracaso}');
                <c:set var="Fracaso" value="" scope="session"></c:set>
            </c:if>
        </script>    
    </body>
</html>
