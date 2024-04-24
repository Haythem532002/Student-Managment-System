package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModifieTab extends JFrame implements ActionListener {
    JButton btn,btnRet;
    EtudiantDAO dao;
    JTextField cinField;
    ModifieTab() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        this.setTitle("Modifie Etudiant");

        JLabel cinLabel = new JLabel("Entrer Le cin de L'étudiant à modifier");
        cinLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        cinLabel.setBounds(125, 40, 400, 30);
        this.add(cinLabel);


        cinField = new JTextField();
        cinField.setBounds(225, 100, 150, 30);
        this.add(cinField);

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


        dao=new EtudiantDAO("jdbc:mysql://localhost:3306/projet_etudiant","root","");

        this.setSize(600, 300);
        this.setLocation(450, 200);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnRet){
            new Home();
            setVisible(false);
        }
        if(e.getSource()==btn){
            int cin=Integer.parseInt(cinField.getText());
            try {
                if(dao.recherche(cin)==-1){
                    JOptionPane.showMessageDialog(this, "Etudiant Introuvable");
                } else {
                    new Modifie(cin);
                    setVisible(false);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public static void main(String[] args) {
        new ModifieTab();
    }
}
