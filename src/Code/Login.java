package Code;

import Data_Base.MyConnexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

public class Login extends JFrame implements ActionListener{

    JTextField emailField;
    JPasswordField passwordField;

    JButton loginbtn;

    Login() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        this.setTitle("Login");
        setLayout(null);

        JLabel userLabel = new JLabel("E-mail : ");
        userLabel.setBounds(40, 20, 100, 30);
        add(userLabel);

        emailField = new JTextField();
        emailField.setBounds(40, 50, 200, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setBounds(40, 80, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 110, 200, 30);
        add(passwordField);

        loginbtn = new JButton("LOGIN");
        loginbtn.setBackground(Color.decode("#751EAA"));
        loginbtn.setBounds(40, 170, 150, 30);
        loginbtn.setForeground(Color.WHITE);
        loginbtn.addActionListener(this);
        this.add(loginbtn);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Images/userProfile.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==loginbtn){
            Socket s= null;
            try {
                s = new Socket("127.0.0.1",9000);
                PrintWriter pr = new PrintWriter(s.getOutputStream());
                pr.println(emailField.getText() + "," + passwordField.getText());
                pr.flush();



                BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
                int ligne = Integer.parseInt(br.readLine());
                if(ligne==1){
                    setVisible(false);
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(null,"Compte Admin Invalid");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
