/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controlador.*;

/**
 *
 * @author z3rh10
 */
@WebServlet(name = "Validacion", urlPatterns = {"/Validacion"})
public class Validacion extends HttpServlet {

    
    public static Usuarios u = null;
    public static PreparedStatement ps;

    public Usuarios validarUsu(String usuario, String pass) throws SQLException {
        
        Usuarios actual = null;
        
        String consulta = "SELECT * FROM USUARIOS WHERE USU = ? AND CONTRA = ?";

        if (ConexionBD.getConexion() == null) {
            ConexionBD.crearConexion();
        }

        try {

            ps = ConexionBD.getConexion().prepareStatement(consulta);

        } catch (SQLException ex) {
            System.err.println("Error consulta");
            System.err.println(ex);
        }

        ResultSet rs;
        try {
            ps.setString(1, usuario);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Validacion correcta");
              
                actual = new Usuarios(
                        rs.getInt("IDU"),
                        rs.getString("USU"),
                        rs.getString("CONTRA"),
                        rs.getString("APODO"),
                        rs.getString("TELEFONO"),
                        rs.getString("EMAIL")
//                        ,
//                        rs.getDate("DIAALTA")
                );

            }
        } catch (SQLException ex) {
                System.err.println(ex);
        }

        return actual;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String usu = request.getParameter("usu");
            String contra = request.getParameter("contra");

            u = validarUsu(usu, contra);


            if (u != null) {
                request.getSession().setAttribute("sesionUsuario", u);
                
                response.sendRedirect("listadoArticulos.jsp");
            } else {
                response.sendRedirect("validacion.jsp");
            }

        }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
