package Data_Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnexion {
    public static Connection get_Connection(String url,String login,String password){
        String nomDriver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(nomDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur Driver : "+e.getMessage());
            return null;
        }
        System.out.println("Driver chargé ...");
        Connection con = null;
        try {
            con = DriverManager.getConnection(url,login,password);
        } catch (SQLException e) {
            System.out.println("Erreur Connection : "+e.getMessage());
            return null;
        }
        return con;
    }
}
