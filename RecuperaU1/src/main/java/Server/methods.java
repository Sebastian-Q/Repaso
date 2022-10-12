package Server;

public class methods {

    public String crearCURP (String nombre, String apellidoPater, String apellidoMaterno, String fechaN){
        String crear = "";

        String ap1 = String.valueOf(apellidoPater.charAt(0));
        String ap2 = String.valueOf(apellidoPater.charAt(1));

        String am1 = String.valueOf(apellidoMaterno.charAt(0));

        String n1 = String.valueOf(nombre.charAt(0)).toUpperCase();

        String f1 = String.valueOf(fechaN.charAt(8));
        String f2 = String.valueOf(fechaN.charAt(9));
        String f3 = String.valueOf(fechaN.charAt(3));
        String f4 = String.valueOf(fechaN.charAt(4));
        String f5 = String.valueOf(fechaN.charAt(0));
        String f6 = String.valueOf(fechaN.charAt(1));

        crear = ap1 + ap2 + am1 + n1 + f1 + f2 + f3 +f4 + f5 + f6;
        return crear;
    }
}
