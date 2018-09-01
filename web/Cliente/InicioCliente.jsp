<%-- 
    Document   : InicioCliente
    Created on : 08-20-2018, 06:00:21 PM
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
        <script type="text/javascript">
            history.forward();
        </script>
        <!-- core CSS -->
        <link href="${base}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${base}/css/font-awesome.min.css" rel="stylesheet">
        <link href="${base}/css/animate.min.css" rel="stylesheet"> 
        <link href="${base}/css/prettyPhoto.css" rel="stylesheet">
        <link href="${base}/css/styles.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/alertify.core.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/alertify.default.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.0.min.js" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/js/alertify.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
    </head>
    <body>
        <jsp:include page="menuCliente.jsp"/>
        <section id="hero-banner">
            <div class="banner-inner">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">                                 
                            <h2>Bienvenido a La Cuponera UDB</h2>
                            <p>Tu sitio online de cupones.</p>

                        </div>
                    </div>
                </div>
            </div>
        </section><!--/#main-slider-->
        <section id="mainservice" >
            <div class="container">
                <div class="section-header">
                    <h2 class="section-title wow fadeInDown">Ofertas de Cupones</h2>
                    <p class="wow fadeInDown">Conoce las ofertas de cupones de diferentes rubros de empresas, ahorra dinero, 
                        ahorra tiempo y conoce muchos lugares.</p>
                    <a class="btn btn-primary" href="${base}/clientes.do?operacion=listar">Ver Ofertas de Cupones</a>
                </div>
            </div>
        </section>
        <section id="mainservice" >
            <div class="container">
                <div class="section-header">
                    <h2 class="section-title wow fadeInDown">Mis Cupones</h2>
                    <p class="wow fadeInDown">Verifica los cupones que has comprado, aquí puede visualizar 
                        los cupones que disponibles, cupones canjeados y sus cupones vencidos.</p>
                    <a class="btn btn-primary" href="${base}/clientes.do?operacion=misCupones">Ver Mis Cupones</a>
                </div>
            </div>
        </section>
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
        <jsp:include page="footerCliente.jsp"/>
    </body>
</html>
