package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class Home extends JFrame implements ActionListener{

    JButton affiche, ajouter, modifier, supprime,chat;

    Home() {
        this.setTitle("Home");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Images/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1120, 630);
        add(image);

        JLabel heading = new JLabel("Systéme de Gestion des Etudiants");
        heading.setBounds(30, 20, 500, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        image.add(heading);

        ajouter = new JButton("Ajouter Etudiant");
        ajouter.setFocusable(false);
        ajouter.setBounds(40, 80, 150, 40);
        ajouter.addActionListener(this);
        image.add(ajouter);

        affiche = new JButton("Afficher Etudiants");
        affiche.setFocusable(false);
        affiche.setBounds(210, 80, 150, 40);
        affiche.addActionListener(this);
        image.add(affiche);

        modifier = new JButton("Modifier Etudiant");
        modifier.setFocusable(false);
        modifier.setBounds(40, 140, 150, 40);
        modifier.addActionListener(this);
        image.add(modifier);

        supprime = new JButton("Supprimer Etudiant");
        supprime.setFocusable(false);
        supprime.setBounds(210, 140, 150, 40);
        supprime.addActionListener(this);
        image.add(supprime);

        chat = new JButton("Ouvrir Chat");
        chat.setFocusable(false);
        chat.setBounds(40, 200, 320, 40);
        chat.addActionListener(this);
        image.add(chat);



        this.setSize(1120, 630);
        this.setLocation(250, 100);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ajouter) {
            setVisible(false);
            new Ajout();

        } else if (ae.getSource() == affiche) {
            setVisible(false);
            try {
                new Affiche();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (ae.getSource() == modifier) {
            setVisible(false);
            new ModifieTab();
        } else if(ae.getSource()==supprime) {
            setVisible(false);
            new Supprime();
        } else {
            setVisible(false);
            String url="rmi://127.0.0.1:8888/chatAdmin";
            ChatRemote cr= null;
            try {
                cr = (ChatRemote) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                throw new RuntimeException(e);
            }
            new Chat(cr);
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
