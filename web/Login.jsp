<%-- 
    Document   : Login
    Created on : Aug 17, 2018, 11:05:57 AM
    Author     : ivanm
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="webthemez">
        <title>Cuponera - Login</title>
        <!-- core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/animate.min.css" rel="stylesheet"> 
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <jsp:include page="/MenuLogin.jsp"/>
        <section>
            <div class="panel panel-default col-lg-6 col-sm-offset-3" style="margin-top: 5%;" >
                <div class="row" >
                    <div class="thumbnail">
                        <h2 style="color:black;">Inicia sessión</h2>
                        <c:if test="${not empty sessionScope.exito}">
                        <div class="alert alert-success">
                            <p>${sessionScope.exito}</p>
                            <c:set var="exito" value="" scope="session"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.fracaso}">
                        <div class="alert alert-danger">
                            <p>${sessionScope.fracaso}</p>
                            <c:set var="fracaso" value="" scope="session"/>
                        </div>
                    </c:if>
                    </div>
                    <div class="col-sm-4 col-sm-offset-4">                    
                            <form action="${base}/usuarios.do" method="POST">
                                <input type="hidden" name="operacion" value="check_login"/>
                                <div class="form-group">
                                    <label for="usuario" style="color: black;">Correo:</label>
                                    <input type="text" name="usuario" id="usuario" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label for="password" style="color: black;">Contraseña:</label>
                                    <input type="password" name="password" id="password" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-lg btn-primary btn-block">Iniciar sesion</button>
                                    <small><a href="${base}/usuarios.do?operacion=registro">No tienes cuenta? registrate aqui!</a></small> 
                                </div>
                            </form>
                        
                    </div>

                </div>
            </div>
        </section>
    </body>
</html>
