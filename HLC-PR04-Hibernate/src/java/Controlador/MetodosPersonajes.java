/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Personajes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import Modelo.*;
import java.util.ArrayList;


/**
 *
 * @author z3rh10
 */
public class MetodosPersonajes {

    public static PreparedStatement ps;
    public static Personajes p = null;
    
    private static ArrayList<Personajes> persList = new ArrayList<Personajes>();
    
    
    public static ArrayList verDatosPersonajes() throws SQLException {

        persList.clear();
        
        String consulta = "SELECT * FROM PERSONAJES ORDER BY IDP";
        if (ConexionBD.getConexion() == null) {
            System.out.println("ERROR CONEXION");
        }

        try {
            ps = ConexionBD.getConexion().prepareStatement(consulta);

        } catch (SQLException ex) {
            System.err.println("Error consulta");
            System.err.println(ex);
        }

        ResultSet rs;
        try {
            rs = ps.executeQuery();

            while (rs.next()) {

                p = new Personajes();

                p.setIdp((Integer) rs.getInt("IDP"));
                p.setNombrep(rs.getString("NOMBREP"));
                p.setCostep((Integer) rs.getInt("COSTEP"));
                p.setRol(rs.getString("ROL"));
                p.setLanzamientop(rs.getDate("LANZAMIENTOP"));
                

                persList.add(p);

            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return persList;
    }
    
    public static ArrayList verDatosPersonajesPorFecha() throws SQLException {

        persList.clear();
        
        String consulta = "SELECT * FROM PERSONAJES ORDER BY LANZAMIENTOP ASC";
        if (ConexionBD.getConexion() == null) {
            System.out.println("ERROR CONEXION");
        }

        try {
            ps = ConexionBD.getConexion().prepareStatement(consulta);

        } catch (SQLException ex) {
            System.err.println("Error consulta");
            System.err.println(ex);
        }

        ResultSet rs;
        try {
            rs = ps.executeQuery();

            while (rs.next()) {

                p = new Personajes();

                p.setIdp((Integer) rs.getInt("IDP"));
                p.setNombrep(rs.getString("NOMBREP"));
                p.setCostep((Integer) rs.getInt("COSTEP"));
                p.setRol(rs.getString("ROL"));
                p.setLanzamientop(rs.getDate("LANZAMIENTOP"));
                

                persList.add(p);

            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return persList;
    }
    
     public static ArrayList verDatosPersonajesPorRol(String rol) throws SQLException {

        persList.clear();
        
        String consulta = "SELECT * FROM PERSONAJES WHERE ROL = '" + rol + "'";
        if (ConexionBD.getConexion() == null) {
            System.out.println("ERROR CONEXION");
        }

        try {
            ps = ConexionBD.getConexion().prepareStatement(consulta);

        } catch (SQLException ex) {
            System.err.println("Error consulta");
            System.err.println(ex);
        }

        ResultSet rs;
        try {
            rs = ps.executeQuery();

            while (rs.next()) {

                p = new Personajes();

                p.setIdp((Integer) rs.getInt("IDP"));
                p.setNombrep(rs.getString("NOMBREP"));
                p.setCostep((Integer) rs.getInt("COSTEP"));
                p.setRol(rs.getString("ROL"));
                p.setLanzamientop(rs.getDate("LANZAMIENTOP"));
                

                persList.add(p);

            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return persList;
    }

    public static int obtNumeroPersonajesTotal() {
        int nPers = 0;

        String consulta = "SELECT COUNT(*) AS ROWCOUNT FROM PERSONAJES";

//        Statement s = c.createStatement();
//        ResultSet rs = s.executeQuery(consulta);
        try {
            ps = ConexionBD.getConexion().prepareStatement(consulta);

        } catch (SQLException ex) {
            System.err.println("Error consulta");
            System.err.println(ex);
        }

        ResultSet rs;
        try {

            rs = ps.executeQuery();

            while (rs.next()) {

                nPers = rs.getInt("ROWCOUNT");
//                System.out.println(nPers);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return nPers;
    }
    
    public static int obtNumeroPersonajesPorRolTotal(String rol) {
        int nPers = 0;

        String consulta = "SELECT COUNT(*) AS ROWCOUNT FROM PERSONAJES WHERE ROL = '" + rol + "'";

//        Statement s = c.createStatement();
//        ResultSet rs = s.executeQuery(consulta);
        try {
            ps = ConexionBD.getConexion().prepareStatement(consulta);

        } catch (SQLException ex) {
            System.err.println("Error consulta");
            System.err.println(ex);
        }

        ResultSet rs;
        try {

            rs = ps.executeQuery();

            while (rs.next()) {

                nPers = rs.getInt("ROWCOUNT");
//                System.out.println(nPers);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return nPers;
    }

    

}
