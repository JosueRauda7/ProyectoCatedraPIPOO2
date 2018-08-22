<%-- 
    Document   : InicioCliente
    Created on : 08-20-2018, 06:00:21 PM
    Author     : admi
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/font-awesome.min.css" rel="stylesheet">
        <link href="../css/animate.min.css" rel="stylesheet"> 
        <link href="../css/prettyPhoto.css" rel="stylesheet">
        <link href="../css/styles.css" rel="stylesheet"> 
    </head>
    <body>
        <jsp:include page="menuCliente.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Bienvenido</h2>
                </div>
            </div>
        </div>
        <jsp:include page="footerCliente.jsp"/>
    </body>
</html>
