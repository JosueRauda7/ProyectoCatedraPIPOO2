<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Pro Fitness center HTML5 web Template | WebThemez</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloverofertas.css">   
        <jsp:include page="enlaces.jsp" />

    </head> 

    <body id="home">
        <jsp:include page="MenuAdmin.jsp" />
        <h1 class="text-center">Ofertas para la empresa</h1>
        <div class="tab">

            <button class="tablinks" onclick="openCity(event, 'Disponibles')">Disponibles</button>
            <button class="tablinks" onclick="openCity(event, 'Canjeados')">Canjeados</button>
            <button class="tablinks" onclick="openCity(event, 'Vencidos')">Vencidos</button>
        </div>
        <section class="contendatos">
            <div id="Disponibles" class="tabcontent">
                <c:forEach var="cuponesDisponibles" items="${requestScope.cuponesDisponibles}">
                    <div class="contenofer">
                        <div  class="text-center">
                            <h3>Codigo cupon:</h3>
                            <h4>${cuponesDisponibles.codigoCupo}</h4>
                        </div>
                        <div  class="text-center">
                            <h3>Fecha compra:</h3>
                            <h4>${cuponesDisponibles.fechaCompra}</h4>
                        </div>
                        <div  class="text-center">
                            <h3>Nombre de la oferta:</h3>
                        <h4>${cuponesDisponibles.oferta.tituloOferta}</h4>
                        </div>
                    </div>
                </c:forEach>
            </div>
             <div id="Canjeados" class="tabcontent">
                <c:forEach var="cuponesCanjeados" items="${requestScope.cuponesCanjeados}">
                    <div class="contenofer">
                        <div  class="text-center">
                            <h3>Codigo cupon:</h3>
                            <h4>${cuponesCanjeados.codigoCupo}</h4>
                        </div>
                        <div  class="text-center">
                            <h3>Fecha compra:</h3>
                            <h4>${cuponesCanjeados.fechaCompra}</h4>
                        </div>
                        <div  class="text-center">
                            <h3>Fecha canje:</h3>
                            <h4>${cuponesCanjeados.fechaCanje}</h4>
                        </div>
                        <div  class="text-center">
                            <h3>Nombre de la oferta:</h3>
                        <h4>${cuponesCanjeados.oferta.tituloOferta}</h4>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div id="Vencidos" class="tabcontent">
                <c:forEach var="cuponesVencidos" items="${requestScope.cuponesVencidos}">
                    <div class="contenofer">
                        <div  class="text-center">
                            <h3>Codigo cupon:</h3>
                            <h4>${cuponesVencidos.codigoCupo}</h4>
                        </div>
                        <div  class="text-center">
                            <h3>Fecha compra:</h3>
                            <h4>${cuponesVencidos.fechaCompra}</h4>
                        </div>
                        <div  class="text-center">
                            <h3>Nombre de la oferta:</h3>
                        <h4>${cuponesVencidos.oferta.tituloOferta}</h4>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <footer id="footer">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        &copy; 2018 Company Name. Template by <a target="_blank" href="https://webthemez.com/sports-fitness/" title="Free Bootstrap Themes and HTML Templates">WebThemez.com</a>
                    </div>
                    <div class="col-sm-6">
                        <ul class="social-icons">
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li> 
                            <li><a href="#"><i class="fa fa-youtube"></i></a></li>
                            <li><a href="#"><i class="fa fa-github"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer><!--/#footer-->
        <script>
            document.getElementById("Disponibles").style.display = "block";
            function openCity(evt, cityName) {

                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(cityName).style.display = "block";

                document.getElementById("exampleModalLabel").innerHTML = cityName;


                evt.currentTarget.className += " active";
            }
        </script>
    </body>
</html>