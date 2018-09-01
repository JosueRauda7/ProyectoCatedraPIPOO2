<%-- 
    Document   : VerCarrito
    Created on : 08-20-2018, 09:00:09 PM
    Author     : admi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="webthemez">
        <title>Cuponera - Cliente</title>
        <!-- core CSS -->
        <script type="text/javascript">
            history.forward();
        </script>
        <!-- core CSS -->
        <link href="${base}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${base}/css/font-awesome.min.css" rel="stylesheet">
        <link href="${base}/css/animate.min.css" rel="stylesheet"> 
        <link href="${base}/css/prettyPhoto.css" rel="stylesheet">
        <link href="${base}/css/styles.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/alertify.core.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/alertify.default.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.0.min.js" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/js/alertify.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
    </head>
    <body>
        <jsp:include page="menuCliente.jsp"/>
        <section class="mainservice">
            <div class="panel panel-default col-lg-8 col-sm-offset-2" style="margin-top: 5%;margin-bottom: 5%;" >
                <div class="row" >
                    <div class="thumbnail">
                        <h2 style="color:black;">Método de Pago</h2>
                        <c:if test="${not empty requestScope.listaErrores}">
                        <div class="alert alert-danger">
                            <ul>
                                <c:forEach var="error" items="${requestScope.listaErrores}">
                                    <li>${error}</li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    </div>
                    <div class="col-lg-6 col-sm-offset-3">                    
                        <form action="${base}/clientes.do" method="POST">
                            <input type="hidden" name="operacion" value="comprarO"/>
                            <div class="form-group">
                                    <label style="color: black;">Seleccione un método de pago:</label>
                                    <select id="metodo" name="metodo" class="form-control">
                                        <option value="1">Visa</option>
                                        <option value="2">MasterCard</option>
                                        <option value="3">American Express</option>
                                        <option value="4">Discover</option>
                                        <option value="5">JCB</option>
                                    </select>
                                </div>
                            <div class="form-group">
                                    <label style="color: black;">No. Tarjeta:</label>
                                    <input type="number" minlength="8" maxlength="8" min="0" name="tarjeta" id="tarjeta" class="form-control" />
                                </div>
                            <div class="form-group">
                                    <label style="color: black;">Mes de expiración:</label>
                                    <input type="number" min="1" max="12" name="mes" id="mes" class="form-control" />
                                </div>
                            <div class="form-group">
                                    <label style="color: black;">Año de expiración:</label>
                                    <input type="number" min="2018" max="2043" name="anio" id="anio" class="form-control" />
                                </div>
                            <div class="form-group">
                                    <label style="color: black;">Código de seguridad:</label>
                                    <input type="number" minlength="3" maxlength="4" min="0" max="9999" name="codSeg" id="codSeg" class="form-control" />
                                </div>
                            <div class="form-group" style="text-align: center;">
                                <button type="submit" class="btn col-xs-5 btn-primary">Finalizar Pago</button>
                                <a class="btn btn-primary col-xs-5 col-sm-offset-2" href="${base}/clientes.do?operacion=cancelarCompra">Cancelar Compra</a><br>
                                <small><a style="text-align: center;" href="${base}/clientes.do?operacion=listar">Regresar</a></small> 
                            </div>
                            </form>
                            </div>
                        

                    </div>

                </div>
            
            
        </section>
                            <jsp:include page="footerCliente.jsp"/>
    </body>
</html>

