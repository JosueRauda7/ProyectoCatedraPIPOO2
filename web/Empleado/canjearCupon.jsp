<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Canjear cupón</title>
        <jsp:include page="/Empleado/head.jsp" />
    </head>
    <body>
        <jsp:include page="/Empleado/menuEmpleado.jsp" />
        <div id="formularioCanje">
            <div class="container-fluid">
                <div class="row">
                    <h3 class="h3">Canjear cupón</h3>
                </div>
                <div class="row">
                    <div class=" col-md-7">
                        <!--<div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>-->
                        <form  role="form" action="${pageContext.request.contextPath}/empleados.do" method="POST">
                            <input type="hidden" name="operacion" value="obtener" />
                            <div class="form-group" id="codigoCupon">
                                <label for="codigo">Código del cupón:</label>
                                <div class="input-group">
                                    <input type="text" class="form-control col-md-7" name="codigo" id="codigo" value="" placeholder="Ingresa el código del cupón" >                                    
                                    <span class="input-group-addon"></span>
                                </div>
                                <input type="submit" class="btn btn-info" id="verificar" value="Verificar" name="Verificar">    
                            </div><br>
                        </form>

                        <div class="form-group">
                            <label for="nombre">Código cupón: ${informacionCupon.codigoCupo}</label><br><br>
                            <label for="fechaCompra">Fecha de compra: ${informacionCupon.fechaCompra}</label><br><br>
                            <label for="fechaCanje">Código canje: ${informacionCupon.fechaCanje}</label>
                            <label for="idCliente">Código canje: ${informacionCupon.cliente.idCliente}</label>
                            <label for="idOferta">Código canje: ${informacionCupon.oferta.idOferta}</label>
                            
                        </div>

                        <input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/editoriales.do?op=listar">Cancelar</a>
                    </div>
                </div> 
            </div>
    </body>
</html>
