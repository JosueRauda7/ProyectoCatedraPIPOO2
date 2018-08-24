<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Pro Fitness center HTML5 web Template | WebThemez</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloagregarempresas.css">   
        <jsp:include page="enlaces.jsp" />

    </head> 

    <body id="home">
        <jsp:include page="MenuAdmin.jsp" />

        <form class="contenform" action="${pageContext.request.contextPath}/empresas.do" method="post"> 
            <h1>Registro empresas</h1>
            <c:if test="${not empty listaErrores}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach var="errores"  items="${requestScope.listaErrores}">
                            <li>${errores}</li>
                            </c:forEach>
                    </ul>
                </div>
            </c:if>
            <input type="hidden" value="editar" name="operacion">
            <input type="hidden" value="${empresa.codigoEmpresa}" name="codigoEmpresa">
            <div class="well well-sm" style="color:#C2185B;"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
            <div class="form-group">            
                <label for="codigo">Nombre de la empresa:</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="nombreEmpresa"  id="codigo" value="${empresa.nombreEmpresa}" placeholder="Ingresa el nombre de la empresa" >
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
            <div class="form-group">            
                <label for="codigo">Nombre del contacto:</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="nombreContacto"  id="codigo" value="${empresa.nombreContacto}" placeholder="Ingresa el nombre del contacto" >
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>

            <div class="form-group">            
                <label for="codigo">Dirección:</label>
                <div class="input-group col-md-12">
                    <textarea class="form-control" placeholder="Ingresa la dirección" name="direccion" style="text-align-last:start; ">${empresa.direccion}</textarea>
                </div>
            </div>
            <div class="form-group">            
                <label for="codigo">Telefono:</label>
                <div class="input-group">
                    <input type="tel" class="form-control" name="telefono"  id="codigo" value="${empresa.telefono}" placeholder="Ingresa el numero de telefono" >
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
            <div class="form-group">            
                <label for="codigo">Rubro al que pertenece:</label>
                <div class="input-group">
                    <select class="form-control" name="rubro" id="rubro">
                        <c:forEach var="rubros" items="${requestScope.listaRubros}">
                            <option value="${rubros.idRubro}">${rubros.rubro}</option>
                        </c:forEach>
                    </select>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
            <div class="form-group">            
                <label for="codigo">Comisión:</label>
                <div class="input-group">
                    <input type="number" step="any" class="form-control" name="comision"  id="codigo" value="${empresa.comision}" placeholder="Ingresa la comisión" >
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
            <button class="btn btn-info" type="submit">Agregar</button>
        </form>

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

            $("#rubro option[value=" + '${empresa.idRubro}' + "]").attr("selected", true);
           
        </script>

    </body>
</html>