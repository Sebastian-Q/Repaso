package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

    public static Connection getConnection () throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/datos",  "root", "");
    }


    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            System.out.println("¡Conexion Exitosa!");
            con.close();
        } catch (SQLException e) {
            System.out.println("¡Conexion Fallida! " + e);
        }
    }
}