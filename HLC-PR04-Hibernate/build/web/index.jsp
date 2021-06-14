<%-- 
    Document   : index.jsp
    Created on : 27-may-2021, 17:49:40
    Author     : z3rh10
--%>

<%@page import="Controlador.ConexionBD"%>
<%@page import="Modelo.Usuarios"%>
<%@page language="java" import="java.util.*" %>
<%@page import="java.io.*,java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Font Awesome Icons -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <!-- Custom CSS Todas las páginas-->
        <link rel="stylesheet" type="text/css" href="./css/all.css">
        <!-- Custom CSS Index-->
        <!-- <link rel="stylesheet" type="text/css" href="assets/css/index.css"> -->

        <!-- Scripts Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

        <script type="text/javascript" >
            function preventBack() {
                window.history.forward();
            }
            setTimeout("preventBack()", 0);
            window.onunload = function () {
                null
            };
        </script>



    </head>
    <body class="bg-dark" id="fondo" ncontextmenu="return false" onselectstart="return false" ondragstart="return false">
        <jsp:useBean id="clock" class="fecha.JspCalendar" />


        <%
            Usuarios usuActivo = (Usuarios) request.getSession().getAttribute("sesionUsuario");

            if (usuActivo != null) {
                //si alguien con sesión iniciada llega al index, la sesión se destruye
                request.getSession().setAttribute("sesionUsuario", null);
                
                //ConexionBD.cerrarConexion();
            }

        %>


        <div class="p-5 text-center">

            <h1 class="p-5">PÁGINA DE INICIO</h1>

            <h3 style="color:white" class="text-center pt-3 pb-3"><%= clock.getDayOfMonth()%>-<%= clock.getMonthInt()%>-<%= clock.getYear()%></h3>

            <h1 class="pb-5"><b>HLC-PR04-Hibernate: JSP/SERVLET[HIBERNATE] con BD</b></h1>


            <h3>Autor:</h3>
            <h1>Sergio Cabral Andrades</h1>

            <hr>
            <h5>2ºDAM</h5>
            <hr>
            <a href="validacion.jsp" class="pr-5">
                <button type="button" class="btn btn-primary">

                    Ir a validación
                </button>
            </a>
        </div>





    </body>
</html>
