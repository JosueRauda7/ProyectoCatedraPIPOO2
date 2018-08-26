<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Pro Fitness center HTML5 web Template | WebThemez</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloverempresas.css">   
        <jsp:include page="enlaces.jsp" />

    </head> 
  <style>
        .alertify-message{
            color: black;
        }
    </style>
    <body id="home">
        <jsp:include page="MenuAdmin.jsp" />
        <h1 class="text-center">Lista empresas</h1>
        <section class="contentabla">
            <table class="table table-bordered" >
                <tr>
                
                <th>Nombre de la empresa</th>
                <th>Nombre contacto</th>
                <th>Dirección</th>
                <th>Telefono</th>
                <th>Rubro</th>
                <th>Comision</th>
                <th>Correo</th>
                <th>Eliminar</th>
                <th>Modificar</th>
                <th>Ofertas</th>
                </tr>
                <c:forEach var="empresas" items="${requestScope.empresas}"> 
                    <tr>
                        
                        <td>${empresas.nombreEmpresa}</td>
                        <td>${empresas.nombreContacto}</td>
                        <td>${empresas.direccion}</td>
                        <td>${empresas.telefono}</td>
                        <td>${empresas.rubro.rubro}</td>
                        <td>$${empresas.comision}</td>
                        <td>${empresas.usuario.correo}</td>
                        <td>
                            <a class="btn btn-info" href="${pageContext.request.contextPath}/administrador.do?operacion=modificarEmpresa&codigo=${empresas.codigoEmpresa}"><span class="glyphicon glyphicon-edit"></span> Modificar</a>
                            
                        </td>
                        <td>
                             <a class="btn btn-danger" href="javascript:eliminar('${empresas.codigoEmpresa}', '${empresas.idUsuario}')"><span class="glyphicon glyphicon-trash"></span> Eliminar</a>
                        </td>
                        <td>
                            <a  class="btn btn-success" href="${pageContext.request.contextPath}/administrador.do?operacion=ofertasEmpresa&codigo=${empresas.codigoEmpresa}"><span class="glyphicon glyphicon-tags"></span>  Ver ofertas</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
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
            
                       <c:if test="${not empty exito}">
                           alertify.success('${exito}');
                          <c:set var="exito" value="" scope="session" />
                       </c:if>
                           <c:if test="${not empty fracaso}">
                           alertify.error('${fracaso}');
                           <c:set var="fracaso" value="" scope="session" />
                       </c:if>
                           
         function eliminar(id,iduser){
           alertify.confirm("¿Realmente decea eliminar esta empresa?", function(e){
              if(e){
                  location.href="administrador.do?operacion=eliminarEmpresa&idemp="+ id+"&iduser="+iduser;
              } 
           });
  }
        </script>

    </body>
</html>