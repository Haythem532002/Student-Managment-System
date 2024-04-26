package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Supprime extends JFrame implements ActionListener {
    EtudiantDAO dao;
    JButton btnRet,btn;
    JComboBox<Integer> comboBoxC;
    Integer[] optionsC;
    Supprime(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        this.setTitle("Supprime Etudiant");
        setLayout(null);

        JLabel cinLabel = new JLabel("Entrer Le cin de L'étudiant à Supprimer");
        cinLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        cinLabel.setBounds(125, 40, 400, 30);
        this.add(cinLabel);


        dao=new EtudiantDAO("jdbc:mysql://localhost:3306/projet_etudiant","root","");

        optionsC=new Integer[5];
        try {
            ResultSet rs=dao.selection("select cin from etudiant");
            int i=-1;
            while(rs.next()){
                i++;
                optionsC[i]=rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        comboBoxC = new JComboBox<>(optionsC);
        comboBoxC.setBounds(225, 100, 150, 30);
        add(comboBoxC);



        btn= new JButton("Continuer");
        btn.setBounds(310, 160, 100, 30);
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.decode("#751EAA"));
        btn.setFocusable(false);
        btn.addActionListener(this);
        this.add(btn);

        btnRet= new JButton("Retourner");
        btnRet.setBounds(190, 160, 100, 30);
        btnRet.setForeground(Color.WHITE);
        btnRet.setBackground(Color.decode("#751EAA"));
        btnRet.setFocusable(false);
        btnRet.addActionListener(this);
        this.add(btnRet);




        this.setSize(600, 300);
        this.setLocation(450, 200);
        this.setVisible(true);

    }
    public static void main(String[] args) {
        new Supprime();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            int cin = (int) comboBoxC.getSelectedItem();
            try {
                int rowsAffected = dao.supprimeEtudiant(cin);
                if (rowsAffected > 0) {
                    comboBoxC.removeItem(cin);
                    JOptionPane.showMessageDialog(this, "Étudiant supprimé avec succès");
                } else {
                    JOptionPane.showMessageDialog(this, "Aucun étudiant trouvé avec ce CIN");
                }
                comboBoxC.setSelectedIndex(1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == btnRet) {
            setVisible(false);
            new Home();
        }
    }
}