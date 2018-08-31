<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Pro Fitness center HTML5 web Template | WebThemez</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloverclientes.css">   
        <jsp:include page="enlaces.jsp" />

    </head> 
    <style>
        .alertify-message{
            color: black;
        }
    </style>
    <body id="home">
        <jsp:include page="MenuAdmin.jsp" />
        <h1 class="text-center">Lista Usuarios</h1>
        <section class="contentabla">
            <table class="table table-bordered"  id="tabla">
                <thead>
                <tr>

                    <th>Id cliente</th>
                    <th>Nombres cliente</th>
                    <th>Apellidos cliente</th>
                    <th>Direccion</th>
                    <th>Dui</th>
                    <th>Correo</th>
                    <th>Ver cupones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="clientes" items="${requestScope.listaClientes}"> 
                    <tr>
                        <td>${clientes.idCliente}</td>
                        <td>${clientes.nombreClientes}</td>
                        <td>${clientes.apellidosClientes}</td>
                        <td>${clientes.direccion}</td>
                        <td>${clientes.dui}</td>
                        <td>${clientes.usuario.correo}</td>
                        <td><button class="btn btn-primary">Ver cupones</button></td>
                    </tr>
                </c:forEach>
                </tbody>
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
            $(document).ready(function () {
                $('#tabla').DataTable();
            });

        </script>
    </body>
</html>