/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author z3rh10
 */
import Modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Modelo.*;

public class MetodosUsuarios {

//    public static Usuarios actual = null;
    public static Usuarios exist = null;
    public static PreparedStatement ps;

    public static Usuarios validarDarAltaUsu(Connection c, String usuario) throws SQLException {

        String consulta = "SELECT * FROM USUARIOS WHERE USU = ?";

//        Statement s = c.createStatement();
//        ResultSet rs = s.executeQuery(consulta);
        try {
            ps = c.prepareStatement(consulta);

        } catch (SQLException ex) {
            System.err.println("Error consulta");
            System.err.println(ex);
        }

        ResultSet rs;
        try {
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Validacion correcta");
                exist = new Usuarios(
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
            System.out.println(ex);
        }

        return exist;
    }
    

    

    public static Usuarios verInfoUsu(Integer idu) throws SQLException {
        String consulta = "SELECT * FROM USUARIOS WHERE IDU = " + idu;
        if (ConexionBD.getConexion() == null) {
            System.out.println("ERROR CONEXION");
        }
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

                exist = new Usuarios(
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
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return exist;
    }

}
