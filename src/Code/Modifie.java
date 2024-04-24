package Code;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modifie extends JFrame implements ActionListener {
    String nom,prenom,filiere,sexe;
    double moyenne;
    int cin;
    JTextField nomField,prenomField,moyenneField,sexeField,filiereField;
    JButton btn,btnRet;
    EtudiantDAO dao;
    JComboBox<String> comboBoxSexe,comboBoxF;

    Modifie(int cin) throws SQLException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        this.setTitle("Modifie Etudiant");

        this.cin=cin;

        JLabel titre = new JLabel("Modifier Les Informations");
        titre.setFont(new Font("Raleway", Font.BOLD, 20));
        titre.setBounds(200, 40, 400, 30);
        this.add(titre);


        JLabel cinLabel = new JLabel("CIN :");
        cinLabel.setBounds(40, 100, 100, 30);
        add(cinLabel);

        JLabel cinLabel2 = new JLabel();
        cinLabel2.setText(String.valueOf(this.cin));
        cinLabel2.setBounds(150, 100, 150, 30);
        add(cinLabel2);


        JLabel nomLabel = new JLabel("Nom :");
        nomLabel.setBounds(40, 180, 100, 30);
        add(nomLabel);

        nomField = new JTextField();
        nomField.setBounds(150, 180, 150, 30);
        add(nomField);

        JLabel prenomLabel = new JLabel("Prénom :");
        prenomLabel.setBounds(40, 140, 100, 30);
        add(prenomLabel);

        prenomField = new JTextField();
        prenomField.setBounds(150, 140, 150, 30);
        add(prenomField);

        JLabel moyenneLabel = new JLabel("Moyenne :");
        moyenneLabel.setBounds(40, 220, 100, 30);
        add(moyenneLabel);

        moyenneField = new JTextField();
        moyenneField.setBounds(150, 220, 150, 30);
        add(moyenneField);

        JLabel filiereLabel = new JLabel("Filière :");
        filiereLabel.setBounds(40, 260, 100, 30);
        add(filiereLabel);

//        filiereField = new JTextField();
//        filiereField.setBounds(150, 260, 150, 30);
//        add(filiereField);

        String[] optionsF = {"FIA", "GL2","GL3", "EEA"};
        comboBoxF = new JComboBox<>(optionsF);
        comboBoxF.setBounds(150, 260, 150, 30);
        add(comboBoxF);

        JLabel sexeLabel = new JLabel("Sexe :");
        sexeLabel.setBounds(40, 300, 100, 30);
        add(sexeLabel);

//        sexeField = new JTextField();
//        sexeField.setBounds(150, 300, 150, 30);
//        add(sexeField);

        String [] optionsSexe= {"Homme","Femme"};
        comboBoxSexe=new JComboBox<>(optionsSexe);
        comboBoxSexe.setBounds(150, 300, 150, 30);
        add(comboBoxSexe);


        btn = new JButton("Continuer");
        btn.setBounds(310, 400, 100, 50);
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.decode("#751EAA"));
        btn.addActionListener(this);
        add(btn);


        btnRet = new JButton("Retourner");
        btnRet.setBounds(190, 400, 100, 50);
        btnRet.setForeground(Color.WHITE);
        btnRet.setBackground(Color.decode("#751EAA"));
        btnRet.addActionListener(this);
        add(btnRet);

        dao = new EtudiantDAO("jdbc:mysql://localhost:3306/projet_etudiant","root","");


        ResultSet rs=dao.selectByCin(this.cin);

        while(rs.next()){
            nom=rs.getString(1);
            prenom=rs.getString(2);
            moyenne=rs.getDouble(4);
            filiere=rs.getString(5);
            sexe=rs.getString(6);
        }

        nomField.setText(nom);
        prenomField.setText(prenom);
        moyenneField.setText(String.valueOf(moyenne));
        comboBoxF.setSelectedItem(filiere);
        comboBoxSexe.setSelectedItem(sexe);
//        filiereField.setText(filiere);
//        sexeField.setText(sexe);



        this.setSize(600, 550);
        this.setLocation(450, 150);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnRet){
            setVisible(false);
            new ModifieTab();
        }
        if(e.getSource()==btn){
            String nouvNom = nomField.getText();
            String nouvPrenom = prenomField.getText();
            double nouvMoyenne = Double.parseDouble(moyenneField.getText());
            String nouvFiliere = (String) comboBoxF.getSelectedItem();
            String nouvSexe = (String) comboBoxSexe.getSelectedItem();
            if(!nouvNom.equalsIgnoreCase(nom) || !nouvPrenom.equalsIgnoreCase(prenom) || nouvMoyenne!=moyenne || !nouvFiliere.equalsIgnoreCase(filiere) || !nouvSexe.equalsIgnoreCase(sexe)){
                try {
                    int x=dao.modifieEtudiant(nouvNom,nouvPrenom,cin,nouvMoyenne,nouvFiliere,nouvSexe);
                    if(x>0){
                        JOptionPane.showMessageDialog(this, "Modfication Effectué avec Succeés");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur de Modifiacation");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pas de changement dans les informations");
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        new Modifie(14367351);
    }
}
