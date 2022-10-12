package Client;

import Controles.Dao;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1300"));
        XmlRpcClient client =new XmlRpcClient();
        client.setConfig(config);

        Scanner sc = new Scanner(System.in);

        Dao dao = new Dao();
        int id = 0;
        int opc = 0;
        String nombre = "", apellidoPater = "", apellidoMaterno = "", fechaNaci = "";
        String consulNombre = "", consulApellido = "", consulCurp = "";

        do {
            System.out.println("MENU\n1.-Registrar Persona \n2.-Consultar a una persona \n3.-Modificar datos una persona \n4.-Consultar todos los datos \n5.-Eliminar persona \n6.-Salir");
            opc = sc.nextInt();
            switch (opc){
                case 1:
                    System.out.println("REGISTRO\nIntroducir Nombre:");
                    nombre = sc.next();
                    System.out.println("Introducir Primer apellido (paterno)");
                    apellidoPater = sc.next();
                    System.out.println("Introducir Segundo Apellido (materno)");
                    apellidoMaterno = sc.next();
                    System.out.println("Introducir Fecha nacimiento (03/07/2002)");
                    fechaNaci = sc.next();

                    Object[] RegistrocrearCurp = new Object[]{nombre, apellidoPater, apellidoMaterno, fechaNaci};
                    String respuesta = (String) client.execute("methods.crearCURP", RegistrocrearCurp);
                    System.out.println("CURP: "+respuesta.toUpperCase());

                    dao.insertarDato(id, nombre, apellidoPater, apellidoMaterno, fechaNaci, respuesta.toUpperCase());
                    break;

                case 2:
                    System.out.println("CONSULTAR PERSONA\nIntroducir nombre");
                    consulNombre = sc.next();
                    System.out.println("Introducir Apellido Primer Apellido (Paterno)");
                    consulApellido = sc.next();
                    Dao.llamarPersona(consulNombre, consulApellido);
                    break;

                case 3:
                    System.out.println("MODIFICAR DATOS\nIntroducir CURP");
                    consulCurp = sc.next();
                    System.out.println("REGISTRO\nIntroducir Nombre:");
                    nombre = sc.next();
                    System.out.println("Introducir Primer apellido (paterno)");
                    apellidoPater = sc.next();
                    System.out.println("Introducir Segundo Apellido (materno)");
                    apellidoMaterno = sc.next();
                    System.out.println("Introducir Fecha nacimiento (03/07/2002)");
                    fechaNaci = sc.next();

                    Object[] Actualizar = new Object[]{nombre, apellidoPater, apellidoMaterno, fechaNaci};
                    String respu = (String) client.execute("methods.crearCURP", Actualizar);
                    System.out.println("CURP: "+respu.toUpperCase());

                    Dao.update(consulCurp,nombre,apellidoPater,apellidoMaterno,fechaNaci,respu.toUpperCase());
                    break;

                case 4:
                    System.out.println("CONSULTAR DATOS");
                    Dao.consultaTodos();
                    break;
                case 5:
                    System.out.println("ELIMINAR\nIntroducir CURP");
                    consulCurp = sc.next();
                    Dao.deletePerson(consulCurp);
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("ERROR NO SE ESCOGIO NINGUNA OPCION");
            }

        }while (opc != 6);
    }

}
