/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author z3rh10
 */
public class ConexionBD {

    private static Connection con;

    public static void crearConexion() throws SQLException{
        try {
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/HLC-PR04-Hibernate", "z3", "z3");
            
        } catch (ClassNotFoundException | SQLException e) {
            
            System.out.println("Conexion incorrecta");     
        }

    }

    public static Connection getConexion() throws SQLException {

        return con;

    }

    public static void cerrarConexion() throws SQLException {
        con.close();
        System.out.println("Conexión cerrada.");
    }

    
}
