package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Affiche extends JFrame implements ActionListener {
    JTable jt_Etudiant;
    MyTableModel model;
    EtudiantDAO dao;
    JButton btn;

    Affiche() throws SQLException {
        this.setTitle("Affiche Etudiants");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        JLabel titre = new JLabel("La Liste des Etudiants : ");
        titre.setBounds(30, 5, 500, 40);
        titre.setFont(new Font("Raleway",Font.BOLD,25));
        this.add(titre);


        jt_Etudiant = new JTable(3,6);

        dao = new EtudiantDAO(Config.JDBC,Config.USERNAME,Config.PASSWORD);
        String req="Select * from etudiant";
        ResultSet rs = dao.selection(req);

        model = new MyTableModel(rs,dao);
        jt_Etudiant.setModel(model);
        JScrollPane jsp = new JScrollPane(jt_Etudiant);
        jsp.setBounds(0, 50,1000,500);
        this.add(jsp);


        btn = new JButton("Retourner");
        btn.setBounds(425, 555, 150, 30);
        btn.setFocusable(false);
        btn.addActionListener(this);
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.decode("#751EAA"));
        this.add(btn);


        this.setSize(1000, 630);
        this.setLocation(310, 100);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn){
            setVisible(false);
            new Home();
        }
    }


    public static void main(String[] args) throws SQLException {
        new Affiche();
    }
}


