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
                    <h2 class="section-title wow fadeInDown">Bienvenido a La Cuponera UDB</h2>
                    <p class="wow fadeInDown">Conoce las ofertas de cupones de diferentes rubros de empresas, ahorra dinero, 
                        ahorra tiempo y conoce muchos lugares.</p>
                </div>
            </div>
        </section>
        <section id="about">
            <div class="container">

                <div class="section-header">
                    <h2 class="section-title wow fadeInDown">About Us</h2>

                </div>

                <div class="row">
                    <div class="col-sm-6 wow fadeInLeft">
                        <img class="img-responsive" src="${base}/images/about.png" alt="">
                    </div>

                    <div class="col-sm-6 wow fadeInRight">
                        <h3 class="column-title">Our Fitness Center</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa semper aliquam quis mattis quam. Morbi vitae tortor tempus, placerat leo et, suscipit lectus. Phasellus ut euismod massa, eu eleifend ipsum.</p>

                        <p>Nulla eu neque commodo, dapibus dolor eget, dictum arcu. In nec purus eu tellus consequat ultricies. Donec feugiat tempor turpis, rutrum sagittis mi venenatis at. Sed molestie lorem a blandit congue. Ut pellentesque odio quis leo volutpat, vitae vulputate felis condimentum. </p>
                        <p class="wow fadeInDown">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa <br> semper aliquam quis mattis quam.</p>
                        <p>Praesent vulputate fermentum lorem, id rhoncus sem vehicula eu. Quisque ullamcorper, orci adipiscing auctor viverra, velit arcu malesuada metus, in volutpat tellus sem at justo.</p>


                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="footerCliente.jsp"/>
    </body>
</html>
