package BD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static  Connection getConexion(){

        Connection conexion = null;
        var baseDato= "FarmaciaSoft";
        var url = "jdbc:mysql://localhost:3306/"+baseDato;
        var usuario="root";
        var password ="admin123";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion= DriverManager.getConnection(url,usuario,password);
        }catch (Exception e ){

            System.out.println("error al conectarnos a la bd" + e.getMessage());
        }

        return conexion;
    }


}
