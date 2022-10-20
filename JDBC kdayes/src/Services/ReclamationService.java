/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDB;
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
public class ReclamationService implements IServiceReclamation {
    Connection connexion;   
  public ReclamationService() {
        connexion = MyDB.getInstance().getConnection();
    }


 
     
 @Override
  public void ajouterReclamation(Reclamation u) throws SQLException {
        String req = "INSERT INTO `reclamation` (`user_id`,`date`,`type`,`contenu`,`img`) "
                + "VALUES (?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, u.getUser_id());
         ps.setDate(2, (java.sql.Date) (Date) u.getDate());
        ps.setString(3, u.getType());
  
         ps.setString(4, u.getContenu());   
ps.setString(5, u.getImg());  
        ps.executeUpdate();
    }
  ////////
    
  //////////
  
@Override
     public List<Reclamation> AfficherAllReclamations() throws SQLException {

        List<Reclamation> rec = new ArrayList<>();
        String req = "select * from reclamation ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Reclamation u = new Reclamation(rst.getInt("id")
                    , rst.getInt("user_id")
                    , rst.getDate("date")
                    , rst.getString("type")
                    , rst.getString("contenu")
                    , rst.getString("img"));
            rec.add(u);
        }
        return rec;
    }
     
  @Override
     public void SupprimerReclamation(Reclamation u) throws SQLException {

        String req = "DELETE FROM reclamation WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
       
       @Override
      public void modifierReclamation(Reclamation u) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE reclamation SET "
                + " user_id='"+u.getUser_id()+"'"
                + ", date='"+u.getDate()+"'"
                + ", type='"+u.getType()+"'"
                 + ", contenu='"+u.getContenu()+"'"
                
                + ", img='"+u.getImg()+"' where id  = "+u.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
      
   
     
     
            
             
      @Override  
   public List<Reclamation> AfficherAllReclamationTriéParDate() throws SQLException {

        List<Reclamation> rec = new ArrayList<>();
        String req = "select * from reclamation order by date ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

         while (rst.next()) {
            Reclamation u = new Reclamation(rst.getInt("id")
                    , rst.getInt("user_id")
                    , rst.getDate("date")
                    , rst.getString("type")
                    , rst.getString("contenu")
                    , rst.getString("img"));
            rec.add(u);
        }
        return rec;
    }
   
   /*
   @Override
   public List<Utilisateur> RechercherUsersParNom(String Nom) throws SQLException {
   List<Utilisateur> users = new ArrayList<>();
            String req = "select  * from  utilisateur u  where u.nom LIKE'" + Nom + "'";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                  Utilisateur u = new Utilisateur(rst.getInt("id")
                    , rst.getString("nom")
                    , rst.getInt("telephone")
                    , rst.getString("email")
                    , rst.getString("password")
                    , rst.getString("usertype"));
               users.add(u);
            }
     return users;
   
    }*/
   
 

   /*   @Override
   public int nbUsersParRole(String Genre) throws SQLException{
       List<Utilisateur> users = new ArrayList<>();
            String req = "select  * from  utilisateur u  where u.usertype LIKE'" + Genre + "'";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                  Utilisateur u = new Utilisateur(rst.getInt("id")
                    , rst.getString("nom")
                    , rst.getInt("telephone")
                    , rst.getString("email")
                    , rst.getString("password")
                    , rst.getString("usertype"));
               users.add(u);
            }
     return users.size();
   }
*/  
}
