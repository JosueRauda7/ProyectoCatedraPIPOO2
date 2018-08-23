<%-- 
    Document   : Home
    Created on : Aug 19, 2018, 2:14:18 PM
    Author     : ivanm
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="webthemez">
        <title>Cuponera - Empleado</title>
        <!-- core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/animate.min.css" rel="stylesheet"> 
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet"> 
        <script src="js/jquery.js" type="text/javascript"></script>
       
        <script src="js/jquery-1.12.0.min.js" type="text/javascript"></script>
         <script src="js/bootstrap.min.js"></script>
         <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>     
        <link href="css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/dataTables.bootstrap.min.js" type="text/javascript"></script>
         
        
       
    </head>
    <body>
        <jsp:include page="/Empresa/MenuEmpresa.jsp"></jsp:include>

            <section style="margin-top: 5%;" >
                <div class="container" style="background-color: white; color: black;">
                    <div class="row ">
                        <h3 style="color:#c2185b; text-align: center;">Lista de empleados</h3>
                    </div>
                    <div class="row">
                        <div class="col-md-14" style="padding: 1%;">
                            <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empleados.do?operacion=nuevo"> Nuevo Empleado</a>
                        <br><br>
                        <table class="table table-striped table-bordered table-hover table-responsive table-condensed " id="tabla">
                            <thead>
                                <tr style="background-color: black; color: white;">
                                    <th>Id empleado</th>
                                    <th>Nombre</th>
                                    <th>Apellido</th>
                                    <th>Correo</th>                                    
                                    <th>Operaciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listaEmpleados}" var="empleados">
                                    <tr style="color:#c2185b;">
                                        <td>${empleados.idEmpleado}</td>
                                        <td>${empleados.nombreEmpleado}</td>
                                        <td>${empleados.apellidoEmpleado}</td>
                                        <td>${empleados.usuario.correo}</td>                                                                               
                                        <td>
                                            <a class="btn btn-info" href="${pageContext.request.contextPath}/empleados.do?operacion=obtener&id=${empleado.idEmpleado}"><span class="glyphicon glyphicon-edit"></span>Editar</a>
                                            <a class="btn btn-danger" href="#"><span class="glyphicon glyphicon-trash"></span>Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
                        <script>
                            $(document).ready(function (){
                                $('#tabla').DataTable();
                            });
                        </script>
            </div>
        </section>
                        
    </body>
</html>