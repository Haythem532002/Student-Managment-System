package Code;

import Data_Base.MyConnexion;

import java.sql.*;

public class EtudiantDAO implements EtudiantDAOCRUD{
    Connection connection=null;
    public EtudiantDAO(String url, String login, String password){
        connection= MyConnexion.get_Connection(url,login,password);
    }


    public int insertEtudiant (String nom, String prenom, int cin, double moyenne,String filiere,String sexe) throws SQLException {
        String req="insert into Etudiant values(?,?,?,?,?,?)";
        PreparedStatement ps=null;
        if(connection!=null){
            ps=connection.prepareStatement(req);
            ps.setString(1,nom);
            ps.setString(2,prenom);
            ps.setInt(3,cin);
            ps.setDouble(4,moyenne);
            ps.setString(5,filiere);
            ps.setString(6,sexe);
        }
        int res=ps.executeUpdate();
        ps.close();
        return res;
    }
    public int recherche(int cin) throws SQLException {
        Statement st=null;
        ResultSet rs=null;
        if(connection!=null){
            st=connection.createStatement();
        }
        if(st!=null){
            rs = st.executeQuery("select * from etudiant where cin = "+cin);
        }
        if(rs.next()){
            return cin;
        } else {
            return -1;
        }
    }
    @Override
    public int supprimeEtudiant(int cin) throws SQLException {
        Statement st=null;
        ResultSet rs=null;
        if(connection!=null){
            st=connection.createStatement();
        }
        if(st!=null){
            rs = st.executeQuery("select * from etudiant where cin = "+cin);
        }
        Statement st2=null;
        if(rs.next()){
            st2=connection.createStatement();
            return st2.executeUpdate("delete from etudiant where cin = "+cin);
        }
        return -1;
    }



    @Override
    public int modifieEtudiant(String nom, String prenom, int cin, double moyenne,String filiere,String sexe) throws SQLException {
        String req = "UPDATE etudiant SET nom = ?, prenom = ?, moyenne = ?, filiere = ?, sexe = ? WHERE cin = ?";
        PreparedStatement ps = null;
        if(connection!=null){
            ps=connection.prepareStatement(req);
            ps.setString(1,nom);
            ps.setString(2,prenom);
            ps.setDouble(3,moyenne);
            ps.setString(4,filiere);
            ps.setString(5,sexe);
            ps.setInt(6,cin);
        }
        int res=ps.executeUpdate();
        ps.close();
        return res;
    }

    public ResultSet selection(String req) throws SQLException {
        ResultSet rs=null;
        Statement st = null;
        if(connection!=null){
            st = connection.createStatement();
        }
        if(st!=null){
            rs=st.executeQuery(req);
        }
        return rs;
    }

    public ResultSet selectByCin(int cin) throws SQLException {
        ResultSet rs=null;
        Statement st = null;
        if(connection!=null){
            st = connection.createStatement();
        }
        if(st!=null){
            rs=st.executeQuery("select * from etudiant where cin="+cin);
        }
        return rs;
    }
    void affiche(ResultSet rs) throws SQLException {
        while(rs.next()){
            String nom=rs.getString(1);
            String prenom=rs.getString(2);
            int cin = rs.getInt(3);
            double moyenne=rs.getDouble(4);
            String filiere=rs.getString(5);
            String sexe=rs.getString(6);
            System.out.println(nom+"  "+prenom+ "  "+cin+ "  "+moyenne+" "+ filiere+" "+sexe);
        }
    }
    @Override
    public void afficheAll(String req) throws SQLException {
        ResultSet rs = selection(req);
        affiche(rs);
    }
}