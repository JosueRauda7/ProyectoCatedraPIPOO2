<%-- 
    Document   : NuevaOferta
    Created on : Aug 20, 2018, 4:32:08 PM
    Author     : ivanm
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Cuponera - Nueva oferta  ${sessionScope.correo}</title>
        <jsp:include page="/Empresa/head.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="/Empresa/MenuEmpresa.jsp"></jsp:include>
            <section>
                <div class="panel panel-default col-lg-6 col-sm-offset-3" style="margin-top: 5%; margin-bottom: 5%;" >
                    <div class="row" >
                        <div class="thumbnail">
                            <h2 style="color:#c2185b;">Nueva Oferta</h2>
                        </div>
                    <c:if test="${not empty requestScope.listaErrores}">
                        <div class="alert alert-danger">
                            <ul>
                                <c:forEach var="error" items="${requestScope.listaErrores}">
                                    <li>${error}</li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <form action="${base}/empresas.do" method="POST" enctype="multipart/form-data" style="padding: 5%;">
                        <div class="form-group">
                            <label for="justificacion" style="color:black;">Justificación</label>
                            <div class="input-group" style>                                
                                <p style="color: red;">${oferta.justificacion}</p>
                            </div>
                        </div>
                        <input type="hidden" name="id" value="${id}" />
                        <input type="hidden" name="operacion" value="modificarOferta"/>
                        <div class="col-md-12" style="padding-bottom: 3%;">
                            <div class="well well-sm"><strong style="color:purple;"><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
                            <div class="form-group">
                                <label for="titulo" style="color:black;">Titulo de la oferta:</label>
                                <div class="input-group">
                                    <input type="text" name="titulo" id="titulo" class="form-control" value="${oferta.tituloOferta}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="precior" style="color:black;">Precio regular:</label>
                                <div class="input-group">
                                    <input type="number" step="0.1" min="0" name="precior" id="precior" class="form-control" value="${oferta.precioRegular}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="precioo" style="color:black;">Precio oferta:</label>
                                <div class="input-group">
                                    <input type="number" min="0" step="0.1" name="precioo" id="precioo" class="form-control" value="${oferta.precioOferta}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fechai" style="color:black;">Fecha inicial de la oferta:</label>
                                <div class="input-group">
                                    <input type="date" name="fechai" id="fechai" class="form-control" maxlength="10" value="${oferta.fechaInicio}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fechaf" style="color:black;">Fecha final de la oferta:</label>
                                <div class="input-group">
                                    <input type="date" name="fechaf" id="fechaf" class="form-control" maxlength="10" value="${oferta.fechaFin}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fechal" style="color:black;">Fecha limite de la oferta:</label>
                                <div class="input-group">
                                    <input type="date" name="fechal" id="fechal" class="form-control" value="${oferta.fechaLimite}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="cantidad" style="color:black;">Cantidad límite de cupones:</label><br>
                                <c:choose>
                                    <c:when test="${oferta.cantidadLimite <= 0}">
                                        <input type="radio" name="limitar" onclick="cantidad.disabled = true" value="0" checked><label style="color:gray">Ilímitado.</label><br>
                                        <input type="radio" name="limitar" onclick="cantidad.disabled = false" value="1" ><label style="color:gray">Limitados.</label>
                                        <div class="input-group">                                    
                                            <input type="number" min="0" step="1" name="cantidad" disabled="true" id="cantidad" class="form-control" value=""/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="limitar" onclick="cantidad.disabled = true" value="0"><label style="color:gray">Ilímitado.</label><br>
                                        <input type="radio" name="limitar" onclick="cantidad.disabled = false" value="1" checked ><label style="color:gray">Limitados.</label>
                                        <div class="input-group">                                    
                                            <input type="number" min="0" step="1" name="cantidad" id="cantidad" class="form-control" value="${oferta.cantidadLimite}"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="form-group">
                                <label for="descripcion" style="color:black;">Descripción:</label>
                                <div class="input-group">
                                    <input type="text" name="descripcion" id="descripcion" class="form-control" value="${oferta.descripcionOferta}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="detalles" style="color:black;">Otros detalles:</label>
                                <div class="input-group">
                                    <input type="text" name="detalles" id="detalles" class="form-control" value="${oferta.otrosDetalles}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="imagen" style="color:black;">Imagen de la oferta:</label>
                                <input data-language="es" type="file" name="archivo" id="imagen" class="form-control file file-loading" data-allowed-file-extensions='["jpg", "png"]'
                                       value="${base}/images/ofertas/${oferta.url_foto}"/>
                            </div>
                            <button type="submit" class="btn" style="background-color: #c2185b">Reenviar</button>
                            <button type="reset" class="btn btn-info">Limpiar</button>
                        </div>
                    </form>

                </div>
            </div>
        </section>

    </body>
</html>
