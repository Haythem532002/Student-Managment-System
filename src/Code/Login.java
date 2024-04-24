package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener{

    JTextField userField;
    JPasswordField passwordField;

    Login() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        this.setTitle("Login");
        setLayout(null);

        JLabel userLabel = new JLabel("Username : ");
        userLabel.setBounds(40, 20, 100, 30);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(40, 50, 200, 30);
        add(userField);

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setBounds(40, 80, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 110, 200, 30);
        add(passwordField);

        JButton loginbtn = new JButton("LOGIN");
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
        if(userField.getText().equalsIgnoreCase("admin") && passwordField.getText().equalsIgnoreCase("admin")){
            setVisible(false);
            new Home();
        } else {
            JOptionPane.showMessageDialog(null,"Username ou password Incorrect");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
