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
                    <div class="col-md-7">
                        <!--<div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>-->
                        <form  role="form" action="${pageContext.request.contextPath}/empleados.do" method="POST">
                            <input type="hidden" name="operacion" value="obtener" />
                            <div class="form-group" id="codigoCupon">
                                <div class="form-group">
                                    <input type="text" class="form-control col-md-7" name="codigo" id="codigo" value="" placeholder="Ingresa el código del cupón" >
                                </div>
                                <input type="submit" class="btn btn-info" id="verificar" value="Verificar" name="Verificar">    
                            </div><br>
                        </form>

                        <form role="form" action="${pageContext.request.contextPath}/empleados.do" method="POST">    
                            <div class="form-group">
                                <c:forEach items="${requestScope.listaCupones}" var="cupones">
                                    <div class="informacionCupon">
                                        <input type="hidden" name="operacion" value="canjear" />
                                        <label for="nombre">Código cupón: ${cupones.codigoCupo}</label><br><br>
                                        <input type="hidden" name="codigoCupon" value="${cupones.codigoCupo}" />
                                        <label for="estadoCupon">Estado: ${cupones.estadoCupon.estado}</label>
                                        <input type="hidden" name="estadoCupon" value="${cupones.estadoCupon.estado}" />
                                        <label for="fechaCompra">Fecha de compra: ${cupones.fechaCompra}</label><div class="fechaC"><label for="fechaCanje" class="fechaCanje">Fecha canje: ${cupones.fechaCanje}</label></div>                                      
                                        <hr class="separador" />
                                    </div>
                                    <div class="informacionCliente">
                                        <!--<label for="duiCliente">DUI:</label><br>-->
                                        <input type="hidden" name="duiComprador" value="${cupones.cliente.dui}" />
                                        <label for="nombreCliente">Cliente: ${cupones.cliente.nombreClientes} ${cupones.cliente.apellidosClientes}</label>
                                        <input type="text" class="form-control col-md-7" name="duiCanjeador" id="duiCanjeador" placeholder="Ingresa el DUI del canjeador"><br>
                                        <hr class="separador" />
                                    </div>
                                    <div class="informacionOferta">
                                        <label for="tituloOferta">Titulo oferta: ${cupones.oferta.tituloOferta}</label>
                                    </div>                               
                                </c:forEach>
                            </div><br>

                            <input type="submit" class="btn btn-success" value="Canjear" name="Canjear">
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/empleados.do?operacion=canje">Cancelar</a>
                        </form>
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
