package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class First_Page extends JFrame implements ActionListener {

    First_Page(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Systeme de Gestion des Etudiants");

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Images/first-Image.jpg"));
        Image i2 = i1.getImage().getScaledInstance(720,360,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(50,100,760,500);
        add(image);

        JLabel label= new JLabel("Systeme de Gestion des Etudiants");
        label.setBounds(50, 70, 450, 50);
        label.setFont(new Font("Raleway",Font.BOLD,25));
        label.setForeground(Color.WHITE);
        image.add(label);


        JButton btn= new JButton("Continuer");
        btn.setBounds(50, 140, 100, 50);
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.decode("#751EAA"));
        btn.setFocusable(false);
        btn.addActionListener(this);
        image.add(btn);



        this.setVisible(true);
        this.setBounds(400,200,760,500);
        this.setLocation(400,200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Login();
    }
}