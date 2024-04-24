package Code;

import java.sql.SQLException;

public interface EtudiantDAOCRUD {
    public int insertEtudiant(String nom,String prenom,int cin,double moyenne,String filiere,String sexe)throws SQLException;
    public int supprimeEtudiant(int cin)throws SQLException;
    public int modifieEtudiant(String nom,String prenom,int cin,double moyenne,String filiere,String sexe) throws SQLException;
    public void afficheAll(String req) throws SQLException ;
}
