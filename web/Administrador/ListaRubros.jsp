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
    <style>
        .alertify-message{
            color: black;
        }
    </style>
    <body id="home">
        <jsp:include page="MenuAdmin.jsp" />
        <h1 class="text-center titulo">Lista rubros</h1>
        <section class="contentabla">
            <table class="table table-bordered" id="tabla">
                <thead>
                <tr>
                    <th>Id rubro</th>
                    <th>Rubro</th>
                    <th>Modificar</th>
                    <th>Eliminar</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.listaRubros}" var="rubro">
                    <tr>
                        <td>${rubro.idRubro}</td>
                        <td><p id="text${rubro.idRubro}">${rubro.rubro}</p>
                            <input type="text" id="txtval${rubro.idRubro}" style="display:none;" class="form-control"></td>
                        <td><button class="btn btn-info" id="btnmostrar${rubro.idRubro}"  ><span class="glyphicon glyphicon-edit"></span> Modificar</button>
                            <section id="conten${rubro.idRubro}" style="display:none;" class="conten">
                                <div style="display:flex;">
                                <form action="${pageContext.request.contextPath}/administrador.do" method="get"> 
                                    <button value="${rubro.idRubro}" id="btnprueba${rubro.idRubro}" name="id" class="btn btn-success">Aceptar</button>
                                    <input type="hidden" value="modificarRubro" name="operacion">
                                    <input type="hidden" name="rubro" id="txtprueba${rubro.idRubro}">
                                </form>
                                <br>
                                <button class="btn btn-danger" id="btncancel${rubro.idRubro}">Cancelar</button>
                                </div>
                            </section>
                        </td>
                        <td><a class="btn btn-danger" href="javascript:eliminar('${rubro.idRubro}')"><span class="glyphicon glyphicon-trash"></span> Eliminar</a></td>
                    </tr>
                <script>
                    document.getElementById("btnmostrar${rubro.idRubro}").addEventListener("click", function(){
          
    
                        document.getElementById("btnmostrar${rubro.idRubro}").style.display="none";
                        document.getElementById("text${rubro.idRubro}").style.display="none";
                        document.getElementById("txtval${rubro.idRubro}").style.display="block";
                        document.getElementById("conten${rubro.idRubro}").style.display="block";
                        document.getElementById("txtval${rubro.idRubro}").value="${rubro.rubro}";
                    });
                    document.getElementById("btnprueba${rubro.idRubro}").addEventListener("click", function(){
                        document.getElementById("txtprueba${rubro.idRubro}").value = document.getElementById("txtval${rubro.idRubro}").value;
                    });
                    document.getElementById("btncancel${rubro.idRubro}").addEventListener("click", function(){
                         document.getElementById("btnmostrar${rubro.idRubro}").style.display="block";
                        document.getElementById("text${rubro.idRubro}").style.display="block";
                        document.getElementById("txtval${rubro.idRubro}").style.display="none";
                        document.getElementById("conten${rubro.idRubro}").style.display="none";
                    });
                </script>
                </c:forEach>
                </tbody>
            </table>
        </section>
        
        
        <form class="contenform" action="${pageContext.request.contextPath}/administrador.do" method="post"> 
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
            <input type="hidden" value="agregarRubro" name="operacion">
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

<script>
            
                       <c:if test="${not empty exito}">
                           alertify.success('${exito}');
                          <c:set var="exito" value="" scope="session" />
                       </c:if>
                           <c:if test="${not empty fracaso}">
                           alertify.error('${fracaso}');
                           <c:set var="fracaso" value="" scope="session" />
                       </c:if>
                           
         function eliminar(id){
           alertify.confirm("¿Realmente decea eliminar este genero?", function(e){
              if(e){
                  location.href="administrador.do?operacion=eliminarRubro&id="+ id;
              } 
           });
  }
        </script>
        <script src="${pageContext.request.contextPath}/js/modificrub.js"></script>
    </body>
</html>