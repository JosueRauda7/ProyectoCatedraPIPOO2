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

  <button class="tablinks" onclick="openCity(event, 'Espera')">En espera</button>
  <button class="tablinks" onclick="openCity(event, 'Futuras')">Aprobadadas futuras</button>
  <button class="tablinks" onclick="openCity(event, 'Activas')">Activas</button>
  <button class="tablinks" onclick="openCity(event, 'Pasadas')">Pasadas</button>
  <button class="tablinks" onclick="openCity(event, 'Rechazadas')">Rechasadas</button>
  <button class="tablinks" onclick="openCity(event, 'Descartadas')">Descartadas</button>
</div>
        <section class="contendatos">
            <div id="Espera" class="tabcontent">
            <c:forEach var="ofertasEspera" items="${requestScope.ofertasEspera}">
                <div class="contenofer">
                    <h3 class="text-center">${ofertasEspera.tituloOferta}</h3>
                    <div id="contenpre">
                        <div>
                            <h5>Precio regular:</h5>
                            <p class="text-center">$${ofertasEspera.precioRegular}</p>
                        </div>
                        <div>
                            <h5>Precio oferta:</h5>
                            <p class="text-center">$${ofertasEspera.precioOferta}</p>
                        </div>
                    </div>
                    <div id="contenfech">
                        <div>
                            <h5>Fecha inicio:</h5>
                            <p class="text-center">${ofertasEspera.fechaInicio}</p>
                        </div>
                        <div>
                            <h5>Fecha fin:</h5>
                            <p class="text-center">${ofertasEspera.fechaFin}</p>
                        </div>
                        <div>
                            <h5>Fecha limite:</h5>
                            <p class="text-center">${ofertasEspera.fechaLimite}</p>
                        </div>
                    </div>
                        <div id="contendescrip">
                            <h5 class="text-center">Descripción oferta:</h5>
                            <p class="text-justify">${ofertasEspera.descripcionOferta}</p>
                        </div>
                        <div id="contendescrip">
                            <h5 class="text-center">Otros detalles:</h5>
                            <p class="text-justify">${ofertasEspera.otrosDetalles}</p>
                        </div>
                       
                            <button type="button" class="btn btn-primary btnmostar" data-toggle="modal" data-target="#exampleModal${ofertasEspera.idOferta}">
  Ver mas de la oferta
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal${ofertasEspera.idOferta}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Datos oferta</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <h1 class="text-center">${ofertasEspera.tituloOferta}</h1>
          <img class="imagenoferta" src="${pageContext.request.contextPath}/images/${ofertasEspera.url_foto}">
          <section class="contencontable">
              <h3>Cupones disponibles:</h3>
              <h4>${ofertasEspera.cantidadLimite} cupones</h4>
              <h3>Ingresos totales:</h3>
              <h4>$${ofertasEspera.ingresos}</h4>
              <h3>Cargo por servicio:</h3>
              <h4>$${ofertasEspera.comision}</h4>
          
          </section>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
                </div>
            </c:forEach>
            </div>
            
            <div id="Futuras" class="tabcontent">
               <c:forEach var="ofertasFuturas" items="${requestScope.ofertasFuturas}">
                <div class="contenofer">
                    <h3 class="text-center">${ofertasFuturas.tituloOferta}</h3>
                    <div id="contenpre">
                        <div>
                            <h5>Precio regular:</h5>
                            <p class="text-center">$${ofertasFuturas.precioRegular}</p>
                        </div>
                        <div>
                            <h5>Precio oferta:</h5>
                            <p class="text-center">$${ofertasFuturas.precioOferta}</p>
                        </div>
                    </div>
                    <div id="contenfech">
                        <div>
                            <h5>Fecha inicio:</h5>
                            <p class="text-center">${ofertasFuturas.fechaInicio}</p>
                        </div>
                        <div>
                            <h5>Fecha fin:</h5>
                            <p class="text-center">${ofertasFuturas.fechaFin}</p>
                        </div>
                        <div>
                            <h5>Fecha limite:</h5>
                            <p class="text-center">${ofertasFuturas.fechaLimite}</p>
                        </div>
                    </div>
                        <div id="contendescrip">
                            <h5 class="text-center">Descripción oferta:</h5>
                            <p class="text-justify">${ofertasFuturas.descripcionOferta}</p>
                        </div>
                        <div id="contendescrip">
                            <h5 class="text-center">Otros detalles:</h5>
                            <p class="text-justify">${ofertasFuturas.otrosDetalles}</p>
                        </div>
                       
                            <button type="button" class="btn btn-primary btnmostar" data-toggle="modal" data-target="#exampleModal${ofertasFuturas.idOferta}">
  Ver mas de la oferta
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal${ofertasFuturas.idOferta}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Datos oferta</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <h1 class="text-center">${ofertasFuturas.tituloOferta}</h1>
          <img class="imagenoferta" src="${pageContext.request.contextPath}/images/${ofertasFuturas.url_foto}">
          <section class="contencontable">
              <h3>Cupones disponibles:</h3>
              <h4>${ofertasFuturas.cantidadLimite} cupones</h4>
              <h3>Ingresos totales:</h3>
              <h4>$${ofertasFuturas.ingresos}</h4>
              <h3>Cargo por servicio:</h3>
              <h4>$${ofertasFuturas.comision}</h4>
          
          </section>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
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
            document.getElementById("Espera").style.display="block";
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
   
        document.getElementById("exampleModalLabel").innerHTML= cityName;
   
    
    evt.currentTarget.className += " active";
}
        </script>
    </body>
</html>