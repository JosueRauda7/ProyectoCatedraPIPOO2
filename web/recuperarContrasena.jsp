<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recupera tu contraseña</title>
        <jsp:include page="head.jsp" />
    </head>
    <body>
        <jsp:include page="/MenuLogin.jsp"/>
        <div id="formularioRecuperarContra">
            <div class="container-fluid">
                <div class="row">
                    <h3 class="h3">Recupera tu contraseña</h3>
                </div><br><br>
                <div class="row">
                    <div class="col-md-7">
                        <form role="form" action="${pageContext.request.contextPath}/usuarios.do" method="POST">
                            <div class="form-group">
                                <div>
                                    <input type="hidden" name="operacion" value="recuperarC" />
                                    <input type="text" class="form-control contra" name="correoElectronico" placeholder="Ingrese su correo electrónico"><br><br>
                                    <input type="submit" class="btn btn-lg btn-primary btn-block" name="recuperarContrasena" id="recuperarContrasena" value="Recuperar contraseña">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>