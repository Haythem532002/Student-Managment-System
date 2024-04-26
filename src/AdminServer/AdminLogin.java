package AdminServer;

import Data_Base.MyConnexion;

import java.io.*;
import java.net.Socket;
import java.sql.*;

public class AdminLogin {

    Socket s;
    Connection connection;
    AdminLogin(Socket s){
        this.s=s;
        connection= MyConnexion.get_Connection("jdbc:mysql://localhost:3306/projet_etudiant","root","");
    }
    public void repond(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line = br.readLine();
            String[] data = line.split(",");
            String email = data[0];
            String password = data[1];






            PrintWriter pr=new PrintWriter(s.getOutputStream());
            Statement st=null;
            ResultSet rs=null;
            PreparedStatement preparedStatement=null;
            if(connection!=null){
                st=connection.createStatement();
            }
            if(st!=null){
                preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE email = ? AND password = ?");
                preparedStatement.setString(1,email);
                preparedStatement.setString(2, password);
                rs = preparedStatement.executeQuery();
            }
            if(rs.next()){
                pr.println(1);
            } else {
                pr.println(0);
            }
            pr.flush();



        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
