<%-- 
    Document   : listaPersonajes
    Created on : 03-jun-2021, 21:25:24
    Author     : z3rh10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.*"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<html>
    <head>
        <title>Listado Por Tablas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Font Awesome Icons -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

        <!-- Custom CSS Todas las páginas-->
        <link rel="stylesheet" type="text/css" href="./css/all.css">




        <!-- Scripts Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>



        <script type="text/javascript">
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

        <nav class="navbar navbar-expand-lg navbar-dark text-center">
            <div class="container-fluid">

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse pt-4" id="navbarNavDropdown">

                    <ul class="navbar-nav">

                        <li class="nav-item">
                            <a class="nav-link px-5 active" href="listaPersonajesArticulos.jsp"><h2><u>LISTADO POR ARTICULOS</u></h2></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link px-5 " href="listaPersonajesTablas.jsp"><h2>LISTADO POR TABLAS</h2></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link px-5" href="index.jsp">
                                <img class="img-fluid logout" alt="logout" src="./img/logout.png">
                            </a>
                        </li>

                    </ul>

                </div>
            </div>
        </nav>
        <%
            Usuarios usuActivo = (Usuarios) request.getSession().getAttribute("sesionUsuario");
            String apodo = "";
            String usu = "";

            if (usuActivo != null) {
                apodo = usuActivo.getApodo();
                usu = usuActivo.getUsu();
            } else {
                response.sendRedirect("validacion.jsp");
            }

            ArrayList<Personajes> persList = new ArrayList();

        %>
        <p class="text-center"><small style="color:white">Hibernate</small></p>
        <h3 class="text-center p-3">Sesión iniciada como: <%= usu%> (<%= apodo%>)</h3>
        <h1 class="text-center">LISTADO DE PERSONAJES POR ARTICULOS</h1>

        <%

            List<Personajes> listaPersonajes = (List<Personajes>) session.getAttribute("listaPersonajes");
            if ((listaPersonajes == null)) {
                PersonajesManager pm = new PersonajesManager();
                listaPersonajes = pm.getPersonajes();
                if (listaPersonajes.size() > 0) {
                    session.setAttribute("listaPersonajes", listaPersonajes);
                }
            }

        %>


        <hr>

        <section class="section-content p-3 text-center">
            <c:forEach var="item" items="${listaPersonajes}">

                <article>

                    <h4 class="text-center p-3">
                        ${item.nombrep}|
                        ${item.costep}<img class="img-fluid pb-2" style="width: 25px; height: auto;" src="./img/esencia.png">|
                        ${item.rol}
                    </h4>
                    <p class="text-center"><img class="img-fluid imgHover" src="./img/personajes/${item.nombrep}.jpg"></p>
                    <h5 class="text-center p-3">Fecha de lanzamiento:  ${item.lanzamientop}</h5>
                    <hr>
                </article>

            </c:forEach>

        </section>


    </body>
</html>
