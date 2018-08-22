<%-- 
    Document   : VerCarrito
    Created on : 08-20-2018, 09:00:09 PM
    Author     : admi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/font-awesome.min.css" rel="stylesheet">
        <link href="../css/animate.min.css" rel="stylesheet"> 
        <link href="../css/prettyPhoto.css" rel="stylesheet">
        <link href="../css/styles.css" rel="stylesheet"> 
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
                            <div class="form-group col-lg-8 col-sm-offset-2">
                                <table style="color:black;" class="table-striped table-bordered table-condensed">
                                    <tr>
                                        <th>Cupón</th>
                                        <th>Precio ($)</th>
                                        <th>Cantidad</th>
                                    </tr>
                                    <tr>
                                        <td>Nombre cupón</td>
                                        <td>precio</td>
                                        <td><input name="cantidad" min="0" type="number"/></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="form-group col-lg-8 col-sm-offset-2">
                                <button type="submit" class="btn btn-lg btn-primary btn-block">Finalizar Compra</button>
                                <small><a style="text-align: center;" href="${base}/usuarios.do?operacion=registro">Regresar</a></small> 
                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </section>
    </body>
</html>
