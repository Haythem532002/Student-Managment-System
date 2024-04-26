package Code;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;


public class Chat extends JFrame implements ActionListener {
    JTextArea textArea;
    JTextField textField;
    JButton button,retBtn;
    ChatRemote cr;
    Chat(ChatRemote cr){
        this.cr=cr;
        setTitle("Admin Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,100,800,600);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Saisir Votre Message");
        southPanel.add(label, BorderLayout.NORTH);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 80));
        textField.setHorizontalAlignment(SwingConstants.CENTER);

        southPanel.add(textField,BorderLayout.CENTER);


        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        button = new JButton("Envoyer");
        button.addActionListener(this);
        btnPanel.add(button);

        retBtn=new JButton("Retourner");
        retBtn.addActionListener(this);
        btnPanel.add(retBtn);

        southPanel.add(btnPanel,BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);
        setVisible(true);

        try {
            new ChatAffiche(cr,textArea).run();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==retBtn){
            setVisible(false);
            new Home();
        }
        if(e.getSource()==button){
            String pseudo=JOptionPane.showInputDialog(null,"Saisir Votre Nom");
            String message=textField.getText();
            Message m=new Message(pseudo,message);
            try {
                cr.addMsg(m);
                textArea.append("Admin:"+m.admin+"\n\t\t Message:\t"+m.message+"\n");
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            textField.setText("");
        }
    }

}


