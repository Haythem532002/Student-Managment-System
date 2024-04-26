package Code;
import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatAffiche {
    ArrayList<Message>arr;
    JTextArea textArea;
    ChatAffiche(ChatRemote cr,JTextArea textArea) throws RemoteException {
        arr=cr.getAllMsg();
        this.textArea=textArea;
    }

    public void run() {
        for (Message msg:arr){
            textArea.append("Admin:"+msg.admin+"\n\t\t Message:\t"+msg.message+"\n");
        }
    }
}

