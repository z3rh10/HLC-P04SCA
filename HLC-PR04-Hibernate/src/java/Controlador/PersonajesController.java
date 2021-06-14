/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author z3rh10
 */
public class PersonajesController extends HttpServlet {

    boolean error = false;
    String mess = "";

    private void doAction(HttpServletRequest request, boolean guardar) {
        error = false;
        Personajes personajes = new Personajes();
        PersonajesManager personajesManager = new PersonajesManager();
        HttpSession session = request.getSession(true);

        if (request.getParameter("idp") != null) {
            int id = (int) 0;
            String param = request.getParameter("idp");

            if (param.length() > 0) {
                id = Integer.parseInt(param);
            }
            if (id != 0) {
                personajes.setIdp(id);
            }
        }
        if (request.getParameter("nombrep") != null) {
            personajes.setNombrep(request.getParameter("nombrep"));
        }
        if (guardar) {
            if (personajesManager.savePersonajes(personajes)) {
                if (session.getAttribute("listaPersonajes") != null) {
                    session.setAttribute("listaPersonajes", null);
                }
                List<Personajes> listaPersonajes = personajesManager.getPersonajes();

                if (listaPersonajes.size() > 0) {
                    session.setAttribute("listaPersonajes", listaPersonajes);
                }
            } else {
                error = true;
                mess = personajesManager.mess;
            }
        } else //Buscar
        {
            if (session.getAttribute("listaPersonajes") != null) {
                session.setAttribute("listaPersonajes", null);
            }
            List<Personajes> listaPersonajes = personajesManager.searchPersonajes(personajes);

            if (listaPersonajes.size() > 0) {
                session.setAttribute("listaPersonajes", listaPersonajes);
            } else {
                error = true;
                mess = "No se encontraron Personajes";
            }

        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String address = "ListaPersonajes.jsp";

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("savePersonajes") != null) {
            address = "ListaPersonajes.jsp";
            doAction(request, true);
            if (error) {
                HttpSession session = request.getSession();
                session.setAttribute("mess", mess);
                address = "AgrEditPersonajes.jsp?error=1";
            }
        } else if (request.getParameter("saveAddPersonajes") != null) {
            address = "AgrEditPersonajes.jsp";
            doAction(request, true);
            if (error) {
                HttpSession session = request.getSession();
                session.setAttribute("mess", mess);
                address = "AgrEditPersonajes.jsp?error=1";
            }
        } else if (request.getParameter("deletePersonajes") != null) {
            address = "ListaPersonajes.jsp";
            if (request.getParameter("idp") != null) {
                int id = Integer.parseInt(request.getParameter("idp"));
                PersonajesManager em = new PersonajesManager();
                Personajes personajes = em.getPersonajes(id);
                try {
                    HttpSession session = request.getSession();
                    if (!em.deletePersonajes(personajes)) {
                        session.setAttribute("mess", mess);
                        address = "EliminarPersonajes.jsp?error=1";
                    }
                    if (session.getAttribute("listaPersonajes") != null) {
                        session.setAttribute("listaPersonajes", null);
                    }
                    List<Personajes> listaPersonajes = em.getPersonajes();

                    if (listaPersonajes.size() > 0) {
                        session.setAttribute("listaPersonajes", listaPersonajes);
                    }
                } catch (Exception ex) {
                    String msg = ex.getLocalizedMessage();
                    HttpSession session = request.getSession();
                    session.setAttribute("mensajeError", msg);
                    address = "Error.jsp";
                }
            }
        } else if (request.getParameter("searchPersonajes") != null) {
            address = "ListaPersonajes.jsp";
            doAction(request, false);
            if (error) {
                HttpSession session = request.getSession();
                session.setAttribute("mess", mess);
                address = "ListaPersonajes.jsp?norecords=1";
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);

        dispatcher.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
