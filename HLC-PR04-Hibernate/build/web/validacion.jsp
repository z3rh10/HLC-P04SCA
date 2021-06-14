<%-- 
    Document   : validacion
    Created on : 27-may-2021, 20:53:01
    Author     : z3rh10
--%>


<%@page import="Modelo.Usuarios"%>
<%@page language="java" import = "java.util.*" %>
<%@page import="java.io.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Validación</title>
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

        <%
            Usuarios usuActivo = (Usuarios) request.getSession().getAttribute("sesionUsuario");

            if (usuActivo != null) {
                //si alguien con sesión iniciada llega a la pantalla de validación, la sesión se destruye
        %>
        <h2 style="color:red;" class="text-center p-3">Error: la sesión ha sido destruida</h2>
        <%
                request.getSession().setAttribute("sesionUsuario", null);
            }

        %>
        <div class="p-5 text-center">
            <h1 class="text-center p-3">VALIDACIÓN</h1>
            <form class="text-center pr-5 pb-5 pl-5" action="UsuariosController">
                <hr>
                <h3>Usuario</h3>
                <input type="text" name="usu"/>
                <br><br>
                <h3>Contraseña</h3>
                <input type="password" name="contra"/>
                <br><br>
                <input type="submit" class="btn btn-primary" value="Validar usuario"/>
            </form> 
        </div>

    </body>
</html>
