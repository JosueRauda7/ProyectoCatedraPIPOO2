<%-- 
    Document   : NuevaOferta
    Created on : Aug 20, 2018, 4:32:08 PM
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
        <title>Cuponera - Nueva oferta  ${sessionScope.correo}</title>
        <!-- core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/animate.min.css" rel="stylesheet"> 
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <jsp:include page="/Empresa/MenuEmpresa.jsp"></jsp:include>
        <section>
            <div class="panel panel-default col-lg-6 col-sm-offset-3" style="margin-top: 5%; margin-bottom: 5%;" >
                <div class="row" >
                    <div class="thumbnail">
                        <h2 style="color:#c2185b;">Nueva Oferta</h2>
                    </div>
                    <c:if test="${not empty requestScope.listaErrores}">
                        <div class="alert alert-danger">
                            <ul>
                                <c:forEach var="error" items="${requestScope.listaErrores}">
                                    <li>${error}</li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <form action="${base}/usuarios.do" method="POST" style="padding: 5%;">
                        <input type="hidden" name="operacion" value="insertar"/>
                        <div class="col-md-12" style="padding-bottom: 3%;">
                            <div class="well well-sm"><strong style="color:purple;"><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
                            <div class="form-group">
                                <label for="nombre" style="color:black;">Nombres:</label>
                                <div class="input-group">
                                    <input type="text" name="nombre" id="nombre" class="form-control" value="${cliente.nombreClientes}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="apellido" style="color:black;">Apellidos:</label>
                                <div class="input-group">
                                    <input type="text" name="apellido" id="apellido" class="form-control" value="${cliente.apellidosClientes}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="direccion" style="color:black;">Dirección:</label>
                                <div class="input-group">
                                    <input type="text" name="direccion" id="direccion" class="form-control" value="${cliente.direccion}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div> 
                            <div class="form-group">
                                <label for="dui" style="color:black;">DUI:</label>
                                <div class="input-group">
                                    <input type="text" name="dui" id="dui" class="form-control" maxlength="10" value="${cliente.direccion}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="correo" style="color:black;">Correo:</label>
                                <div class="input-group">
                                    <input type="text" name="correo" id="correo" class="form-control" value="${usuario.correo}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                                    <div class="form-group">
                                <label for="contra" style="color:black;">Contraseña:</label>
                                <div class="input-group">
                                    <input type="password" name="contra" id="contra" class="form-control" value="${usuario.contrasenia}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>  
                                    <button type="submit" class="btn" style="background-color: #c2185b">Registrar</button>
                            <button type="reset" class="btn btn-info">Limpiar</button>
                        </div>
                    </form>

                </div>
            </div>
        </section>
    </body>
</html>
