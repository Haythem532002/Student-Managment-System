package AdminServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminMain {
    public static void main(String[] args) {
        System.out.println("Lancement de Server...");
        try {
            ServerSocket server=new ServerSocket(9000);
            System.out.println("Server en ecoute...");
            while(true){
                Socket s=server.accept();
                new AdminLogin(s).repond();

            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
