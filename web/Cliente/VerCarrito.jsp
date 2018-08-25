<%-- 
    Document   : VerCarrito
    Created on : 08-20-2018, 09:00:09 PM
    Author     : admi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="webthemez">
        <title>Cuponera - Cliente</title>
        <!-- core CSS -->
        <link href="${base}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${base}/css/font-awesome.min.css" rel="stylesheet">
        <link href="${base}/css/animate.min.css" rel="stylesheet"> 
        <link href="${base}/css/prettyPhoto.css" rel="stylesheet">
        <link href="${base}/css/styles.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/alertify.core.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/alertify.default.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.0.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/alertify.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
    </head>
    <body>
        <jsp:include page="menuCliente.jsp"/>
        <section>
            <div class="panel panel-default col-lg-8 col-sm-offset-2" style="margin-top: 5%;" >
                <div class="row" >
                    <div class="thumbnail">
                        <h2 style="color:black;">Artículos Seleccionados</h2>
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
                    <div class="col-lg-8 col-sm-offset-2">                    
                        <form action="${base}/usuarios.do" method="POST">
                            <div class="form-group">
                                <table style="color:black; width: 100%;" id="tabla" class="table-striped table-bordered table-condensed">
                                    <thead>
                                        <tr>
                                            <th>Cupón</th>
                                            <th>Precio ($)</th>
                                            <th>Cantidad</th>
                                            <th>Cancelar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="oferta" items="${ofertas}" varStatus="loop">
                                        <tr>
                                            <td>${oferta.getTituloOferta()}</td>
                                            <td>${oferta.getPrecioOferta()}</td>
                                            <td><input name="cantidad" min="0" type="number" value="1"/></td>
                                            <td><a class="btn btn-primary" href="javascript:cancelar('${loop.index}')">Cancelar</a></td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="form-group col-lg-8 col-sm-offset-2">
                                <button type="submit" class="btn btn-lg btn-primary btn-block">Finalizar Compra</button>
                                <small><a style="text-align: center;" href="${base}/clientes.do?operacion=listar">Regresar</a></small> 
                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </section>
        <script>
            $(document).ready(function () {
                $('#tabla').DataTable();
            });
            function cancelar(id){
                alertify.confirm('¿Desea retirar este cupón del carrito de compras?', function (e) {
                    if (e) {
                        location.href = '${base}/clientes.do?operacion=cancelar&id=' + id;
                    }
                });
            }
        </script>
    </body>
</html>
