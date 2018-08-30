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
        <title>Cuponera - listaOfertas</title>
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
        <link href="css/alertify.core.css" rel="stylesheet" type="text/css"/>
        <link href="css/alertify.default.css" rel="stylesheet" type="text/css"/>
        <script src="js/alertify.js" type="text/javascript"></script>
        
    </head>
    <body>
        <jsp:include page="/Empresa/MenuEmpresa.jsp"></jsp:include>

            <section style="margin-top: 5%;" >
                <div class="container" style="background-color: white; color: black;">
                    <div class="row ">
                        <h3 style="color:#c2185b; text-align: center;">Lista de ofertas de ${sessionScope.correo}</h3>
                </div>
                <div class="row">
                    <div class="col-md-14" style="padding: 1%;">
                        <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=nuevaOferta"> Nueva Oferta</a><br><br>                            
                        <div >
                            <h4 style="color:#c2185b;">Filtrar ofertas</h4>
                            <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=listarOferta&tipoOferta=0">Todas</a>
                            <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=listarOferta&tipoOferta=1">En espera</a>
                            <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=listarOferta&tipoOferta=2">Aprovadas</a>
                            <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=listarOferta&tipoOferta=3">Activas</a>
                            <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=listarOferta&tipoOferta=4">Finalizadas</a>
                            <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=listarOferta&tipoOferta=5">Rechazadas</a>
                            <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=listarOferta&tipoOferta=6">Descartadas</a>
                        </div>
                        <br><br>
                        <table class="table table-striped table-bordered table-hover table-responsive table-condensed " id="tabla">
                            <thead>
                                <tr style="background-color: black; color: white;">
                                    <th>Id Oferta</th>
                                    <th>Titulo</th>
                                    <th>Existencias</th>
                                    <th>Precio de oferta</th>
                                    <th>Fecha de publicación</th>
                                    <th>Fecha de vigencia</th>
                                    <th>Estado</th>
                                    <th>Imagen</th>                                    
                                    <th>Operaciones</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listaOfertas}" var="ofertas">
                                    <tr style="color:#c2185b;">
                                        <td>${ofertas.idOferta}</td>
                                        <td>${ofertas.tituloOferta}</td>
                                        <c:choose>
                                            <c:when test="${ofertas.cantidadLimite!=-1}">
                                                
                                                <td>${ofertas.cantidadLimite}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>Ilimitada</td>
                                            </c:otherwise>
                                        </c:choose>
                                        
                                        <td>$${ofertas.precioOferta}</td>
                                        <td>${ofertas.fechaInicio}</td>
                                        <td>${ofertas.fechaLimite}</td>
                                        <td>${ofertas.estadoOferta.estado}</td>
                                        <td><img height="100px" src="${base}/images/ofertas/${ofertas.url_foto}"/></td>
                                        <td>
                                            <a title="Detalles" class="btn btn-default" href="javascript:detalles('${ofertas.idOferta}')"><span class="glyphicon glyphicon-search"></span></a>
                                            
                                            <c:if test="${ofertas.estadoOferta.estado eq 'Rechazada' }">
                                                <a title="Editar" class="btn btn-info" href="${pageContext.request.contextPath}/empresas.do?operacion=obtenerOferta&id=${ofertas.idOferta}"><span class="glyphicon glyphicon-edit"></span></a>                                            
                                            </c:if>
                                            
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal fade" id="modal" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title"></h4>
                                </div>
                                <div class="modal-body">
                                    <ul class="list-group">
                                        <li class="list-group-item"><b>Cupones vendidos: </b><span id="CuponesVendidos"></span></li>   
                                        <li class="list-group-item"><b>Cupones disponibles: </b><span id="CuponesDisponibles"></span></li>
                                        <li class="list-group-item"><b>Ingresos totales:</b>$<span id="IngresosTotales"></span></li>   
                                        <li class="list-group-item"><b>Cargo por servicios: </b>$<span id="Cargoporservicios"></span></li>   
                                        <li class="list-group-item"><b>Descripción: </b><span id="Descripcion"></span></li>   
                                        <li class="list-group-item"><b>Detalles: </b><span id="Detalles"></span></li>   
                                          
                                    </ul>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>                          
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->

                </div>
                <script>
                    $(document).ready(function () {
                        $('#tabla').DataTable();
                    });
                    <c:if test="${not empty exito}">
                    alertify.success('${exito}');
                        <c:set var="exito" value="" scope="session"/>
                    </c:if>
                    <c:if test="${not empty fracaso}">
                    alertify.error('${fracaso}');
                        <c:set var="fracaso" value="" scope="session"/>
                    </c:if>

                    function detalles(id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/empresas.do?operacion=detalleOferta&id=" + id,
                            type: "GET",
                            dataType: "JSON",
                            success: function (data) {
                                $('#CuponesVendidos').text(data.CuponesVendidos);
                                $('#CuponesDisponibles').text(data.CuponesDisponibles);
                                $('#IngresosTotales').text(data.IngresosTotales);
                                $('#Cargoporservicios').text(data.Cargoporservicios);
                                $('#Descripcion').text(data.Descripcion);
                                $('#Detalles').text(data.Detalles);                                
                                $('.modal-title').text(data.Titulo);
                                $('#modal').modal('show');
                            }
                        });
                    }
                </script>
            </div>
        </section>

    </body>
</html>
