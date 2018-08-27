<%-- 
    Document   : NuevaOferta
    Created on : Aug 20, 2018, 4:32:08 PM
    Author     : ivanm
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Cuponera - Nueva oferta  ${sessionScope.correo}</title>
        <jsp:include page="/Empresa/head.jsp"></jsp:include> 
    </head>
    <body>
        <jsp:include page="/Empresa/MenuEmpresa.jsp"></jsp:include>
        <section>
            <div class="panel panel-default col-lg-6 col-sm-offset-3" style="margin-top: 5%; margin-bottom: 5%;" >
                <div class="row" >
                    <div class="thumbnail">
                        <h2 style="color:#c2185b;">Nuevo Empleado</h2>
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
                        <input type="hidden" name="operacion" value="insertarE"/>
                        <div class="col-md-12" style="padding-bottom: 3%;">
                            <div class="well well-sm"><strong style="color:purple;"><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
                            <div class="form-group">
                                <label for="nombre" style="color:black;">Nombres:</label>
                                <div class="input-group">
                                    <input type="text" name="nombre" id="nombre" class="form-control" value="${empleado.nombreEmpleado}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="apellido" style="color:black;">Apellidos:</label>
                                <div class="input-group">
                                    <input type="text" name="apellido" id="apellido" class="form-control" value="${empleado.apellidoEmpleado}"/>
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
                            <button type="submit" class="btn" style="background-color: #c2185b">Registrar</button>
                            <button type="reset" class="btn btn-info">Limpiar</button>
                        </div>
                    </form>

                </div>
            </div>
        </section>
    </body>
</html>
