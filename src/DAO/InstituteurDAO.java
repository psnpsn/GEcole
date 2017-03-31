
package DAO;
import Models.Eleve;
import Models.Instituteur;
import ODB.OracleDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InstituteurDAO implements DAO<Instituteur>  {
     private String            nomTable    = "INST"    ;
    private String            nomSequence = "SEQ_ID_E" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;
    
    public InstituteurDAO(){
              session = OracleDBSingleton.getSession();
    }

@Override
    public ObservableList<Instituteur> getAll() {
        ArrayList<Instituteur> liste = new ArrayList<Instituteur>();
        try {
            requete = "SELECT * FROM " + nomTable ;
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                Instituteur instituteur = new Instituteur();
           
                
                instituteur.setId_i(resultat.getInt("ID"));
                instituteur.setNom(resultat.getString("NOM"));
                instituteur.setPrenom(resultat.getString("PRENOM"));
                instituteur.setNCIN(resultat.getInt("NCIN"));
                instituteur.setDateNaiss(resultat.getDate("DATENAISS"));
                instituteur.setDateEmb(resultat.getDate("DATEEMB"));
                instituteur.setSex(resultat.getString("SEX"));
                instituteur.setGrade(resultat.getString("GRADE"));
                instituteur.setAdresse(resultat.getString("ADRESSE"));
                instituteur.setVille(resultat.getString("VILLE"));
                instituteur.setCodeP(resultat.getInt("CODEP"));
                instituteur.setImmatricul(resultat.getInt("IMM"));
                instituteur.setEmail(resultat.getString("EMAIL"));
                instituteur.setTel1(resultat.getInt("TEL"));
                instituteur.setTel2(resultat.getInt("TEL2"));
            liste.add(instituteur);
            }
        }catch(Exception exception){
            System.out.println("Classe : InstituteurDAO.java\n"
                    + "Methode : getAll()\n"
                    + "Exception : " + exception);
        }
        ObservableList<Instituteur> list = FXCollections.observableArrayList(liste);
    return list;
    }
       @Override
    public boolean delAll() {
        valide = false;
        try {
            requete = "DELETE FROM " + nomTable;
            statement = session.prepareStatement(requete);
           if ( statement.executeUpdate()!=0)
                valide = true;
        } catch (Exception exception) {
            System.out.println("Classe : InstituteurDAO.java\n"
                    + "Methode : delAll()\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

 
    public int create(Instituteur instance) {
    valide = false;
        try {
            requete = "INSERT INTO " + nomTable + " (ID , NOM , PRENOM , DATENAISS ,NCIN, DATEEMB , SEX , IMM , GRADE , ADRESSE ,VILLE , CODEP , TEL, TEL2, EMAIL  )  "
                      + "  VALUES ( " + seq_id_next() + " , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setString(2, instance.getPrenom());
            statement.setDate(3,new java.sql.Date(instance.getDateNaiss().getTime()));
            statement.setInt(4, instance.getNCIN());
            statement.setDate(5,new java.sql.Date(instance.getDateEmb().getTime()));
            statement.setString(6,String.valueOf(instance.getSex()));
            statement.setInt(7,instance.getImmatricul());
            statement.setString(8, instance.getGrade());
            statement.setString(9, instance.getAdresse());
            statement.setString(10, instance.getVille());
            statement.setInt(11, instance.getCodeP());
            statement.setInt(12,instance.getTel1());
            statement.setInt(13,instance.getTel2());
            statement.setString(14, instance.getEmail());


            if (statement.executeUpdate() != 0) {
                seq=seq_id_curr();
            }
        } catch (Exception exception) {
            System.out.println("Classe : InstituteurDAO.java\n"
                    + "Methode : create(Instituteur instance)\n"
                    + "Exception : " + exception);
        }
        return seq;
    }
  @Override
    public Instituteur find(int id) {
      Instituteur instituteur = null;
        try {
            requete = "SELECT * FROM " + nomTable +" WHERE ( ID = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                instituteur = new Instituteur();
                instituteur.setId_i(resultat.getInt("ID"));
                instituteur.setNom(resultat.getString("NOM"));
                instituteur.setPrenom(resultat.getString("PRENOM"));
                instituteur.setNCIN(resultat.getInt("NCIN"));
                instituteur.setDateNaiss(resultat.getDate("DATENAISS"));
                instituteur.setDateEmb(resultat.getDate("DATEEMB"));
                instituteur.setSex(resultat.getString("SEX"));
                instituteur.setGrade(resultat.getString("GRADE"));
                instituteur.setAdresse(resultat.getString("ADRESSE"));
                instituteur.setVille(resultat.getString("VILLE"));
                instituteur.setCodeP(resultat.getInt("CODEP"));
                instituteur.setImmatricul(resultat.getInt("IMM"));
                instituteur.setEmail(resultat.getString("EMAIL"));
                instituteur.setTel1(resultat.getInt("TEL"));
                instituteur.setTel2(resultat.getInt("TEL2"));
          
            }

        } catch (Exception exception) {
            System.out.println("Classe : InstituteurDAO.java\n"
                    + "Methode : findByID()\n"
                    + "Exception : " + exception);
        }
        return instituteur;
    }
   
    public boolean update(Instituteur instance) {
    valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET  ( "
                    + "NOM           =  ?  ,"
                    + "PRENOM        =  ?  ,"
                    + "DATENAISS      =  ?  ,"
                    + "NCIN        =  ?  ,"
                    + "DATEEMB    =  ?  ,"
                    + "SEX =  ?  ,"
                    + "IMM =  ?  ,"
                    + "GRADE         =  ?  ,"
                    + "ADRESSE  =  ?  ,"
                    + "VILLE  =  ?  ,"
                    + "CODEP         =  ?  ,"
                    + "TEL    =  ?  ,"
                    + "TEL2    =  ?  ,"
                    + "EMAIL   =  ? )"
                    + "WHERE  ID   = ? ";
            statement = session.prepareStatement(requete);
      
            statement.setString(1, instance.getNom());
            statement.setString(2, instance.getPrenom());
            statement.setDate(3,new java.sql.Date(instance.getDateNaiss().getTime()));
            statement.setInt(4, instance.getNCIN());
            statement.setDate(5,new java.sql.Date(instance.getDateEmb().getTime()));
            statement.setString(6,String.valueOf(instance.getSex()));
            statement.setInt(7,instance.getImmatricul());
            statement.setString(8, instance.getGrade());
            statement.setString(9, instance.getAdresse());
            statement.setString(10, instance.getVille());
            statement.setInt(11, instance.getCodeP());
            statement.setInt(12,instance.getTel1());
            statement.setInt(13,instance.getTel2());
            statement.setString(14, instance.getEmail());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : InstituteurDAO.java\n"
                    + "Methode : update(Instituteur instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }
    
    
    
    
    
    @Override
    public boolean delete(int id) {
        valide = false;
        try {
            requete = "DELETE FROM " + nomTable + " WHERE ( ID= ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : InstituteurDAO.java\n"
                    + "Methode : delete(instituteur instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }
    
    private int seq_id_next(){
        try {
            requete = "SELECT " +nomSequence+ ".nextval FROM DUAL";
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                seq=resultat.getInt("NEXTVAL");
            }

        } catch (Exception exception) {
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : seq_id_next\n"
                    + "Exception : " + exception);
        }
        System.out.println("sequence nextval "+seq);
        return seq;
    }
    
    public int seq_id_curr(){
    try {
            requete = "SELECT " +nomSequence+ ".currval FROM DUAL";
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                seq=resultat.getInt("CURRVAL");
            }

        } catch (Exception exception) {
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : seq_id_curr\n"
                    + "Exception : " + exception);
        }
       
        System.out.println("sequence curr  "+seq);
        return seq;
    }

}
