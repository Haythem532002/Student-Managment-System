package Code;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    ArrayList<Object[]> data = new ArrayList<Object[]>();
    ResultSetMetaData rsmd;
    EtudiantDAO dao;
    MyTableModel(ResultSet rs, EtudiantDAO dao) throws SQLException {
        this.dao=dao;
        rsmd=rs.getMetaData();
        while(rs.next()){
            Object [] ligne = new Object[rsmd.getColumnCount()];
            for(int i=0;i<ligne.length;i++){
                ligne[i] = rs.getObject(i+1);
            }
            data.add(ligne);
        }
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column+1);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(getColumnName(columnIndex).equalsIgnoreCase("cin")){
            return false;
        } else {
            return true;
        }
    }
    int columnNameToIndex(String columnName){
        for(int i=0;i<getColumnCount();i++){
            if(getColumnName(i).equalsIgnoreCase(columnName)){
                return i;
            }
        }
        return -1;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String nom = getValueAt(rowIndex,columnNameToIndex("nom")).toString();
        String prenom = getValueAt(rowIndex,columnNameToIndex("prenom")).toString();
        int cin = Integer.parseInt(getValueAt(rowIndex,columnNameToIndex("cin")).toString());
        double moyenne = Double.parseDouble(getValueAt(rowIndex,columnNameToIndex("moyenne")).toString());
        String filiere = getValueAt(rowIndex,columnNameToIndex("filiere")).toString();
        String sexe = getValueAt(rowIndex,columnNameToIndex("sexe")).toString();

        if(getColumnName(columnIndex).equalsIgnoreCase("nom")){
            nom=aValue.toString();
        }

        if(getColumnName(columnIndex).equalsIgnoreCase("prenom")){
            prenom=aValue.toString();
        }
        if(getColumnName(columnIndex).equalsIgnoreCase("moyenne")){
            moyenne=Double.parseDouble(aValue.toString()) ;
        }
        if(getColumnName(columnIndex).equalsIgnoreCase("cin")){
            cin=Integer.parseInt(aValue.toString());
        }
        if(getColumnName(columnIndex).equalsIgnoreCase("filiere")){
            prenom=aValue.toString();
        }
        if(getColumnName(columnIndex).equalsIgnoreCase("sexe")){
            prenom=aValue.toString();
        }

        int a=0;
        try {
            a = dao.modifieEtudiant(nom,prenom,cin,moyenne,filiere,sexe);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(a>0){
            data.get(rowIndex)[columnIndex]=aValue;
        }
    }

    public void insertEtudiant(String nom ,String prenom,int cin, double moyenne,String filiere,String sexe) throws SQLException {
        int a=dao.insertEtudiant(nom,prenom,cin,moyenne,filiere,sexe);
        if(a>0){
            data.add(new Object[]{nom,prenom,cin,moyenne});
            fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(null,"Not Inserted");
        }
    }
    //Click droit affiche supprimer
    public void supprimerEtudiant(int rowIndex){
        int cin = (int) data.get(rowIndex)[2];
        System.out.println(cin);
        int a=0;
        try {
            a=dao.supprimeEtudiant(cin);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(a>0){
            data.remove(rowIndex);
            fireTableDataChanged();
        }

    }

}
