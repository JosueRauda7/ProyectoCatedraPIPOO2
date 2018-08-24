<%-- 
    Document   : MisCuponesClientes
    Created on : 08-20-2018, 08:39:34 PM
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
        <link href="${base}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${base}/css/font-awesome.min.css" rel="stylesheet">
        <link href="${base}/css/animate.min.css" rel="stylesheet"> 
        <link href="${base}/css/prettyPhoto.css" rel="stylesheet">
        <link href="${base}/css/styles.css" rel="stylesheet">
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
        <section id="mainservice" >
            <div class="container">
                <div class="section-header">
                    <h2 class="section-title wow fadeInDown">Mis Cupones</h2>
                    <p class="wow fadeInDown">Verifica los cupones que has comprado, aquí puede visualizar 
                        los cupones que disponibles, cupones canjeados y sus cupones vencidos.</p>
                </div>
                <section>
                    <div class="col-sm-6 col-md-12">

                        <form class="form-inline" action="${base}/clientes.do" method="POST">
                            <input type="hidden" name="operacion" value="categorizar"/>
                            <div class="form-group">
                                <div class="col-md-4">
                                    <h4>Filtro: </h4>
                                </div>
                                <div class="col-xs-6">
                                    <select id="categoriaFiltro" name="categoriaFiltro" class="form-control">
                                        <c:forEach var="estadito" items="${requestScope.estado}">
                                            <c:choose>
                                                <c:when test="${estadito.getIdEstadoCupon() eq categoria}">
                                                    <option value="${estadito.getIdEstadoCupon()}" selected="true">${estadito.getEstado()}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${estadito.getIdEstadoCupon()}">${estadito.getEstado()}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <input type="submit" name="filtrar" class="btn btn-primary" value="Filtrar"/>
                                </div>
                            </div>
                        </form>

                    </div>
                    <br><br><br>
                </section>
                <div class="row">
                    <c:forEach var="oferta" items="${requestScope.ofertita}">
                        <div class="col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img class="img-responsive" src="${base}/images/${oferta.getUrl_foto()}" alt="">
                                <div class="caption">
                                    <h3>${oferta.getTituloOferta()}</h3>
                                    <p>Descripción:<br>${oferta.getDescripcionOferta()}</p>
                                    <p class="price">Fecha Compra: ${oferta.getFechaCompra()}</p>
                                    <p class="price">Fecha Límite: ${oferta.getFechaLimite()}</p>
                                    <p><h4>Precio Regular: $ ${oferta.getPrecioRegular()}</h4></p>
                                    <p><h4>Precio Oferta: $ ${oferta.getPrecioOferta()}</h4></p>
                                    <a class="btn btn-primary" href="${base}/clientes.do?operacion=facturar&id=${oferta.getCodigoCupo()}">Obtener Factura</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </div>
        </section>
        <script>
            $(document).ready(function () {
                $('#categoriaFiltro').select2();
            });
        </script>
        <jsp:include page="footerCliente.jsp"/>
    </body>
</html>
