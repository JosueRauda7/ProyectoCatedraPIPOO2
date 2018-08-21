<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Pro Fitness center HTML5 web Template | WebThemez</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilorubros.css">   
        <jsp:include page="enlaces.jsp" />

    </head> 

    <body id="home">
        <jsp:include page="MenuAdmin.jsp" />
        <h1 class="text-center titulo">Lista rubros</h1>
        <section class="contentabla">
            <table class="table table-bordered">
                <tr>
                    <th>Id rubro</th>
                    <th>Rubro</th>
                    <th>Modificar</th>
                    <th>Eliminar</th>
                </tr>
                <c:forEach items="${requestScope.listaRubros}" var="rubro">
                    <tr>
                        <td>${rubro.idRubro}</td>
                        <td>${rubro.rubro}</td>
                        <td><button class="btn btn-info">Modificar</button></td>
                        <td><button class="btn btn-danger">Eliminar</button></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
        
        
        <form class="contenform" action="${pageContext.request.contextPath}/rubros.do" method="post"> 
            <h1>Registro rubros</h1>
            <c:if test="${not empty listaErrores}">
                    <div class="alert alert-danger">
                        <ul>
                            <c:forEach var="errores"  items="${requestScope.listaErrores}">
                                <li>${errores}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    </c:if>
            <input type="hidden" value="agregar" name="operacion">
            <div class="well well-sm" style="color:#C2185B;"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
            <div class="form-group">            
                <label for="codigo">Nombre del rubro:</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="rubro"  id="codigo" value="${rubro.rubro}" placeholder="Ingresa el nombre del rubro" >
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


    </body>
</html>