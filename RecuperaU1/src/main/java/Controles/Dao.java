package Controles;

import Util.ConnectionMySQL;

import java.sql.*;

public class Dao {
    static Connection con;
    static PreparedStatement pstm;
    static ResultSet rs;
    static Statement st;

    String newRegister = "INSERT INTO personas(id, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, curp) VALUE (?,?,?,?,?,?)";

    public datosPersonas insertarDato(int id, String nombre, String apellidoPater, String apellidoMaterno, String fechaNaci, String curp){
        datosPersonas datePerson = new datosPersonas();
        try {
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareStatement(newRegister);
            pstm.setInt(1, id);
            pstm.setString(2, nombre);
            pstm.setString(3, apellidoPater);
            pstm.setString(4, apellidoMaterno);
            pstm.setString(5, fechaNaci);
            pstm.setString(6, curp);
            int resultado =pstm.executeUpdate();
            if (resultado == 1){
                datePerson.setId(id);
                datePerson.setNombre(nombre);
                datePerson.setApellidoPaterno(apellidoPater);
                datePerson.setApellidoMaterno(apellidoMaterno);
                datePerson.setFechaNacimiento(fechaNaci);
                datePerson.setCurp(curp);
            }else{
                datePerson = null;
            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo InsertarDato: " + e.getMessage());
        }finally {
            try {
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar las conexiones: " + e.getMessage());
            }
        }
        return datePerson;
    }


    static String consultarPersona = "SELECT id, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, curp FROM personas WHERE nombre=? && apellidoPaterno=?;";
    public static datosPersonas llamarPersona (String nombre, String apellidoPater){
        datosPersonas consulPerson = new datosPersonas();
        try {
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareStatement(consultarPersona);
            pstm.setString(1, nombre);
            pstm.setString(2, apellidoPater);
            rs = pstm.executeQuery();
            if (rs.next()){
                consulPerson.setId(rs.getInt("id"));
                consulPerson.setNombre(rs.getString("nombre"));
                consulPerson.setApellidoPaterno(rs.getString("apellidoPaterno"));
                consulPerson.setApellidoMaterno(rs.getString("apellidoMaterno"));
                consulPerson.setFechaNacimiento(rs.getString("fechaNacimiento"));
                consulPerson.setCurp(rs.getString("curp"));

                System.out.println("Id: " + consulPerson.getId());
                System.out.println("Nombre:" + consulPerson.getNombre());
                System.out.println("Apellido Paterno: " + consulPerson.getApellidoPaterno());
                System.out.println("Apellido Materno: " + consulPerson.getApellidoMaterno());
                System.out.println("Fecha Nacimiento: " + consulPerson.getFechaNacimiento());
                System.out.println("CURP: " + consulPerson.getCurp());
            }else {
                System.out.println("No se encontro a la persona");
            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo Consultar 1 persona: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar las conexiones: " + e.getMessage());
            }
        }
        return consulPerson;
    }

    static String actualizarPersona = "UPDATE personas set nombre=?, apellidoPaterno=?, apellidoMaterno=?, fechaNacimiento=?, curp=? WHERE curp=?;";
    public static datosPersonas update (String curpVieja, String nombre, String apellidoPater, String apellidoMaterno, String fechaNaci, String curpNueva) {
        datosPersonas updatePerson = new datosPersonas();
        try {
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareStatement(actualizarPersona);
            pstm.setString(1,nombre);
            pstm.setString(2,apellidoPater);
            pstm.setString(3,apellidoMaterno);
            pstm.setString(4,fechaNaci);
            pstm.setString(5,curpNueva);
            pstm.setString(6,curpVieja);
            pstm.executeUpdate();
            System.out.println("Se actualizo exitosamente");

        } catch (SQLException e) {
            System.out.println("Error en el metodo Actualizar persona: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar las conexiones: " + e.getMessage());
            }
        }
        return updatePerson;
    }

    public static datosPersonas consultaTodos () {
        datosPersonas consultPerson = new datosPersonas();
        try {
            con = ConnectionMySQL.getConnection();
            String query = "SELECT * FROM personas;";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                consultPerson.setId(rs.getInt("id"));
                consultPerson.setNombre(rs.getString("nombre"));
                consultPerson.setApellidoPaterno(rs.getString("apellidoPaterno"));
                consultPerson.setApellidoMaterno(rs.getString("apellidoMaterno"));
                consultPerson.setFechaNacimiento(rs.getString("fechaNacimiento"));
                consultPerson.setCurp(rs.getString("curp"));


                System.out.println("Id: " + consultPerson.getId());
                System.out.println("Nombre:" + consultPerson.getNombre());
                System.out.println("Apellido Paterno: " + consultPerson.getApellidoPaterno());
                System.out.println("Apellido Materno: " + consultPerson.getApellidoMaterno());
                System.out.println("Fecha Nacimiento: " + consultPerson.getFechaNacimiento());
                System.out.println("CURP: " + consultPerson.getCurp());
                System.out.println("---------------------------------------------------------------------");
            }
        }catch (SQLException e) {
            System.out.println("Error en el metodo Consulta General: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar las conexiones: " + e.getMessage());
            }
        }

        return consultPerson;
    }


    static String eliminar = "DELETE FROM personas WHERE curp=?;";
    public static datosPersonas deletePerson(String curp) {
        datosPersonas consulPerson = new datosPersonas();
        try {
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareStatement(eliminar);
            pstm.setString(1, curp);
            pstm.executeUpdate();
            System.out.println("Se Elimino Exitosamente");
        } catch (SQLException e) {
            System.out.println("Error en el metodo Delete persona: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar las conexiones: " + e.getMessage());
            }
        }
        return consulPerson;
    }

}
