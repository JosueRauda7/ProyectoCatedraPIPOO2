<%-- 
    Document   : ComprarCupones
    Created on : 08-20-2018, 06:47:01 PM
    Author     : admi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat" %>
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
        <section id="mainservice" >
            <div class="container">
                <div class="section-header">
                    <h2 class="section-title wow fadeInDown">Ofertas de Cupones</h2>
                    <p class="wow fadeInDown">Conoce las ofertas de cupones de diferentes rubros de empresas, ahorra dinero, 
                        ahorra tiempo y conoce muchos lugares.</p>
                </div>
                <section>
                    <div class="col-sm-6 col-md-12">

                        <form class="form-inline" action="${base}/clientes.do" method="POST">
                            <input type="hidden" name="operacion" value="filtrar"/>
                            <div class="form-group">
                                <div class="col-md-4">
                                    <h4>Rubro: </h4>
                                </div>
                                <div class="col-xs-6">
                                    <select id="rubroFiltros" name="rubroFiltro" class="form-control">
                                        <option value="0">Todos</option>
                                        <c:forEach var="rubrito" items="${requestScope.rubrito}">
                                            <c:choose>
                                                <c:when test="${rubrito.getIdRubro() eq rubro}">
                                                    <option value="${rubrito.getIdRubro()}" selected="true">${rubrito.getRubro()}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${rubrito.getIdRubro()}">${rubrito.getRubro()}</option>
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
                                    <p class="price">Fecha fin: ${oferta.getFechaFin()}</p>
                                    <p class="price">Fecha Límite: ${oferta.getFechaLimite()}</p>
                                    <p><h4>Precio Regular: $ ${oferta.getPrecioRegular()}</h4></p>
                                    <p><h4>Precio Oferta: $ ${oferta.getPrecioOferta()}</h4></p>
                                    <a class="btn btn-primary" href="#">Ver Detalles</a>
                                    <a class="btn btn-primary" href="javascript:agregar(${oferta.getIdOferta()})">Añadir al Carrito</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#rubroFiltro').select2();
            });
            function agregar(id){
                alertify.confirm('¿Desea agregar este cupón del carrito de compras?', function (e) {
                    if (e) {
                        location.href = '${base}/clientes.do?operacion=agregar&id=' + id;
                    }
                });
            }
        </script>
        <jsp:include page="footerCliente.jsp"/>
    </body>
</html>

