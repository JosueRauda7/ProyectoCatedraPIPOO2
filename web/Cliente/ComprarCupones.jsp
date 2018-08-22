<%-- 
    Document   : ComprarCupones
    Created on : 08-20-2018, 06:47:01 PM
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
                                    <select id="rubroFiltro" name="rubroFiltro" class="form-control">
                                        <option value="0">Todos</option>
                                        <option value="2">Cupones Canjeados</option>
                                        <option value="3">Cupones Vencidos</option>
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
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="../images/portfolio/01.jpg" alt="">
                            <div class="caption">
                                <h3>Título Cupón</h3>
                                <p>Descripción Oferta</p>
                                <p class="price">Fecha fin</p>
                                <p class="price">Fecha Límite</p>
                                <p><h4>Precio Regular</h4></p>
                                <p><h4>Precio Oferta</h4></p>
                                <a class="btn btn-primary" href="#">Ver Detalles</a>
                                <a class="btn btn-primary" href="#">Añadir al Carrito</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="../images/portfolio/02.jpg" alt="">
                            <div class="caption">
                                <h3>Workout</h3>
                                <p>Praesent vulputate fermentum lorem, id rhoncus sem vehicula eu. Quisque ullamcorper, orci adipiscing auctor viverra, veli</p>
                                <p class="price">Mon to Fri: 7am to 6pm</p>
                                <p><h4>$90.00/mo</h4></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="../images/portfolio/03.jpg" alt="">
                            <div class="caption">
                                <h3>Aerobic</h3>
                                <p>Praesent vulputate fermentum lorem, id rhoncus sem vehicula eu. Quisque ullamcorper, orci adipiscing auctor viverra, veli</p>
                                <p class="price">Mon to Fri: 7am to 6pm</p>
                                <p><h4>$90.00/mo</h4></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script>
            $(document).ready(function () {
                $('#rubroFiltro').select2();
            });
        </script>
        <jsp:include page="footerCliente.jsp"/>
    </body>
</html>

