package Code;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class Ajout extends  JFrame implements ActionListener {
    JTextField nomField, prenomField, cinField, sexeField, moyenneField;
    EtudiantDAO dao;
    JButton btn,btnRet;
    JComboBox<String> comboBoxSexe,comboBoxF;
    Ajout() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        this.setTitle("Ajout Etudiant");
        setLayout(null);

        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setBounds(40, 20, 100, 30);
        add(nomLabel);

        nomField = new JTextField();
        nomField.setBounds(150, 20, 150, 30);
        add(nomField);

        JLabel prenomLabel = new JLabel("Prénom:");
        prenomLabel.setBounds(40, 60, 100, 30);
        add(prenomLabel);

        prenomField = new JTextField();
        prenomField.setBounds(150, 60, 150, 30);
        add(prenomField);

        JLabel cinLabel = new JLabel("CIN:");
        cinLabel.setBounds(40, 100, 100, 30);
        add(cinLabel);

        cinField = new JTextField();
        cinField.setBounds(150, 100, 150, 30);
        add(cinField);

        JLabel moyenneLabel = new JLabel("Moyenne:");
        moyenneLabel.setBounds(40, 140, 100, 30);
        add(moyenneLabel);

        moyenneField = new JTextField();
        moyenneField.setBounds(150, 140, 150, 30);
        add(moyenneField);

        String[] optionsF = {"FIA", "GL2","GL3"};
        comboBoxF = new JComboBox<>(optionsF);
        comboBoxF.setBounds(150, 180, 150, 30);
        add(comboBoxF);

        JLabel filiereLabel = new JLabel("Filière:");
        filiereLabel.setBounds(40, 180, 100, 30);
        add(filiereLabel);


        JLabel sexeLabel = new JLabel("Sexe:");
        sexeLabel.setBounds(40, 220, 100, 30);
        add(sexeLabel);

        String [] optionsSexe= {"Homme","Femme"};
        comboBoxSexe=new JComboBox<>(optionsSexe);
        comboBoxSexe.setBounds(150, 220, 150, 30);
        add(comboBoxSexe);



        btn = new JButton("Continuer");
        btn.setBounds(310, 280, 100, 50);
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.decode("#751EAA"));
        btn.addActionListener(this);
        add(btn);


        btnRet = new JButton("Retourner");
        btnRet.setBounds(190, 280, 100, 50);
        btnRet.setForeground(Color.WHITE);
        btnRet.setBackground(Color.decode("#751EAA"));
        btnRet.addActionListener(this);
        add(btnRet);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Images/ajout.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 50, 200, 200);
        add(image);

        dao = new EtudiantDAO("jdbc:mysql://localhost:3306/projet_etudiant","root","");

        setSize(600, 410);
        setLocation(450, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnRet){
            setVisible(false);
            new Home();
        }
        if (e.getSource()==btn) {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int cin = Integer.parseInt(cinField.getText());
            double moyenne = Double.parseDouble(moyenneField.getText());
            String filiere = (String) comboBoxF.getSelectedItem();
            String sexe = (String) comboBoxSexe.getSelectedItem();

            try {
                if(dao.recherche(cin)==-1){
                    try {
                        int rowsAffected = dao.insertEtudiant(nom, prenom, cin, moyenne, filiere, sexe);
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(this, "Étudiant ajouté avec succès");
                            nomField.setText("");
                            prenomField.setText("");
                            cinField.setText("");
                            moyenneField.setText("");
                            comboBoxF.setSelectedIndex(0);
                            comboBoxSexe.setSelectedIndex(0);
                        } else {
                            JOptionPane.showMessageDialog(this, "Échec de l'ajout de l'étudiant");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Etudiant Existe Deja avec ce CIN");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public static void main(String[] args) {
        new Ajout();
    }
}
