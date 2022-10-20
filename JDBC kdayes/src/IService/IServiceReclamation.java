/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.Reclamation;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public interface IServiceReclamation {
 public List<Reclamation> AfficherAllReclamationTriéParDate() throws SQLException;

   public void ajouterReclamation(Reclamation u) throws SQLException;
     public List<Reclamation> AfficherAllReclamations() throws SQLException;
       public void SupprimerReclamation(Reclamation u) throws SQLException;
         public void modifierReclamation(Reclamation u) throws SQLException, NoSuchAlgorithmException;
}
