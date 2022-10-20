/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import DB.MyDB;
import Entities.Assurance;
import Entities.Reclamation;
import IService.IServiceReclamation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class AssuranceService {
        Connection connexion;   
  public AssuranceService() {
        connexion = MyDB.getInstance().getConnection();
    }

    
    
       

  public void ajouterAssurance(Assurance u) throws SQLException {
        String req = "INSERT INTO `assurance` (`Datesoin`,`IdentifiantAssure`,`Nom`,`Prenom`,`Qualite`,`CodeApci`,`CodeAct`,`Cotation`,`MontantHoraire`,`Ticket`,`MontantCharge`,`CodeConventionnel`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setDate(1,(java.sql.Date) (Date) u.getDatesoin());
         ps.setInt(2, u.getIdentifiantAssure());
        ps.setString(3, u.getNom());
  
         ps.setString(4, u.getPrenom());
         ps.setString(5, u.getQualite());
         ps.setString(6, u.getCodeApci());
         ps.setString(7, u.getCodeAct());
         ps.setInt(8, u.getCotation());
         ps.setInt(9, u.getMontantHoraire());
         ps.setInt(10, u.getTicket());
         ps.setInt(11, u.getMontantCharge() );
        ps.setString(12, u.getCodeConventionnel());  
        ps.executeUpdate();
    }

  

     public List<Assurance> AfficherAllAssurance() throws SQLException {

        List<Assurance> assu = new ArrayList<>();
        String req = "select * from assurance  ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Assurance u = new Assurance(rst.getInt("id")
                    , rst.getDate("Datesoin")
                    , rst.getInt("IdentifiantAssure")
                    , rst.getString("Nom")
                    , rst.getString("Prenom")
                     , rst.getString("Qualite")
                     , rst.getString("CodeApci")
                     , rst.getString("CodeAct")
                     , rst.getInt("Cotation")
                     , rst.getInt("MontantHoraire")
                     , rst.getInt("Ticket")
                     , rst.getInt("MontantCharge")
                    , rst.getString("CodeConventionnel"));
            assu.add(u);
        }
        return assu;
    }
     
 
     public void SupprimerAssurance(Assurance u) throws SQLException {

        String req = "DELETE FROM assurance WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
      //   + " categorie_emploi_id ='"+e.getCategorie_emploi_id()+"'"
       // + ", date_exp='" + (java.sql.Date) (Date) e.getDate_exp()+"'"
      public void modifierAssurance(Assurance u) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE assurance SET "
                + "Datesoin ='"   +  (java.sql.Date) (Date) u.getDatesoin()+"'"
                + ", IdentifiantAssure='"+u.getIdentifiantAssure()+"'"
                + ", Nom='"+u.getNom()+"'"
                + ", Prenom='"+u.getPrenom()+"'"
                + ", Qualite='"+u.getQualite()+"'"
                + ", CodeApci='"+u.getCodeApci()+"'"
                + ", CodeAct='"+u.getCodeAct()+"'"
                + ", Cotation='"+u.getCotation()+"'"
                + ", MontantHoraire='"+u.getMontantHoraire()+"'"
                + ", Ticket='"+u.getTicket()+"'"
                + ", MontantCharge='"+u.getMontantCharge()+"'"
                + ", CodeConventionnel='"+u.getCodeConventionnel()+"' where id  = "+u.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
      
   
     
     
            
             
    

   

}
