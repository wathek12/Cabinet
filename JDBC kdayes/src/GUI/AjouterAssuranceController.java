/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Assurance;
import Entities.Reclamation;
import Services.AssuranceService;
import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class AjouterAssuranceController implements Initializable {
    public static Assurance connectedAssurance;
  AssuranceService cs = new AssuranceService();
    @FXML
    private TextField inputIdentifiant;
    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputPrenom;
    @FXML
    private TextField inputQualite;
    @FXML
    private TextField inputCodeApci;
    @FXML
    private TextField inputcodeact;
    @FXML
    private TextField inputcotation;
    private TextField inputmontant;
    @FXML
    private TextField inputticket;
    @FXML
    private TableView<Assurance> tableview;
    @FXML
    private TableColumn<?, ?> datetableview;
    @FXML
    private TableColumn<?, ?> identifianttableview;
    @FXML
    private TableColumn<?, ?> nomtableview;
    @FXML
    private TableColumn<?, ?> prenomtableview;
    @FXML
    private TableColumn<?, ?> qualitétableview;
    @FXML
    private TableColumn<?, ?> codeapcitableview;
    @FXML
    private TableColumn<?, ?> codeacttableview;
    @FXML
    private TableColumn<?, ?> cotationtableview;
    @FXML
    private TableColumn<?, ?> montanttableview;
    @FXML
    private TableColumn<?, ?> ticketableview;
    @FXML
    private TableColumn<?, ?> codetableview;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private DatePicker datesoins;
    @FXML
    private TextField inputmontantHon;
    @FXML
    private TextField inputmontantch;
    @FXML
    private TextField inputcodeconven;
    @FXML
    private Label labelid;
    @FXML
    private Button Confirmer;
    @FXML
    private Hyperlink prec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         AssuranceService pss = new AssuranceService();
        ArrayList<Assurance> c = new ArrayList<>();
        try {
            c = (ArrayList<Assurance>) pss.AfficherAllAssurance();
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        ObservableList<Assurance> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        datetableview.setCellValueFactory(new PropertyValueFactory<>("Datesoin"));
        identifianttableview.setCellValueFactory(new PropertyValueFactory<>("IdentifiantAssure"));
        nomtableview.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenomtableview.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        qualitétableview.setCellValueFactory(new PropertyValueFactory<>("Qualite"));
        codeapcitableview.setCellValueFactory(new PropertyValueFactory<>("CodeApci"));
        codeacttableview.setCellValueFactory(new PropertyValueFactory<>("CodeAct"));
        cotationtableview.setCellValueFactory(new PropertyValueFactory<>("Cotation"));
        montanttableview.setCellValueFactory(new PropertyValueFactory<>("MontantHoraire"));
        ticketableview.setCellValueFactory(new PropertyValueFactory<>("Ticket"));
        montanttableview.setCellValueFactory(new PropertyValueFactory<>("MontantCharge"));
        codetableview.setCellValueFactory(new PropertyValueFactory<>("CodeConventionnel"));
             
          
    }    

    @FXML
    private void insert(ActionEvent event) throws SQLException {
        
            AssuranceService productService = new AssuranceService();
  
        if (inputIdentifiant.getText().equals("") || inputNom.getText().equals("") || inputNom.getText().equals("") 
                || inputPrenom.getText().equals("") 
                || inputQualite.getText().equals("") 
             
              ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisi tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputPrenom.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
                || inputNom.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
                                || inputQualite.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
              ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        else{
    

             java.util.Date date2
                = java.util.Date.from(this.datesoins.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
            Assurance c = new Assurance(sqlDate2,Integer.parseInt(inputIdentifiant.getText()), inputNom.getText(),
                    inputPrenom.getText(), inputQualite.getText(),
                    inputCodeApci.getText(), inputcodeact.getText(),
                    Integer.parseInt(inputcotation.getText()), Integer.parseInt(inputmontantHon.getText()),
                    Integer.parseInt( inputticket.getText()), Integer.parseInt(inputmontantch.getText()),
                      inputcodeconven.getText() );
                   
                           
            
            productService.ajouterAssurance(c);
     
     resetTableData(); 
        
        } 
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
         if (event.getSource() == supprimer) {
            Assurance rec = new Assurance();
            rec.setId(tableview.getSelectionModel().getSelectedItem().getId());  
            AssuranceService cs = new AssuranceService();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Are you sure you want to delete this assurance ");
            a.setHeaderText(null);
            a.showAndWait();
            cs.SupprimerAssurance(rec);
            resetTableData(); 
       
    }
         
    }
     public void resetTableData() throws SQLDataException, SQLException {

        List<Assurance> listrec = new ArrayList<>();
        listrec = cs.AfficherAllAssurance();
        ObservableList<Assurance> data = FXCollections.observableArrayList(listrec);
        tableview.setItems(data);
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
     
           AssuranceService ps = new AssuranceService();
         
           
   Assurance as = new Assurance(tableview.getSelectionModel().getSelectedItem().getDatesoin());

           connectedAssurance = as;
           
                 
       java.sql.Date r;
        
                 r = new java.sql.Date(connectedAssurance.getDatesoin().getTime());
        LocalDate date = r.toLocalDate();  
       
       labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));
       
       datesoins.setValue( date);
           inputIdentifiant.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getIdentifiantAssure()));
        inputNom.setText(tableview.getSelectionModel().getSelectedItem().getNom());
        inputPrenom.setText(tableview.getSelectionModel().getSelectedItem().getPrenom());
          inputQualite.setText(tableview.getSelectionModel().getSelectedItem().getQualite());
            inputCodeApci.setText(tableview.getSelectionModel().getSelectedItem().getCodeApci());
              inputcodeact.setText(tableview.getSelectionModel().getSelectedItem().getCodeAct());
                inputcotation.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getCotation()));
                  inputmontantHon.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getMontantHoraire()));
                  inputticket.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getTicket()));
                  inputmontantch.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getMontantCharge()));
                  inputcodeconven.setText(tableview.getSelectionModel().getSelectedItem().getCodeConventionnel());
    Confirmer.setVisible(true);
    
    
    }

    @FXML
    private void Confirmer(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        
          AssuranceService productService = new AssuranceService();
  
        if (inputIdentifiant.getText().equals("") || inputNom.getText().equals("") || inputNom.getText().equals("") 
                || inputPrenom.getText().equals("") 
                || inputQualite.getText().equals("") 
             
              ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisi tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputPrenom.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
                || inputNom.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
                                || inputQualite.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
              ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        else{
    

             java.util.Date date2
                = java.util.Date.from(this.datesoins.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
            Assurance c = new Assurance(Integer.parseInt(labelid.getText()),sqlDate2,Integer.parseInt(inputIdentifiant.getText()), inputNom.getText(),
                    inputPrenom.getText(), inputQualite.getText(),
                    inputCodeApci.getText(), inputcodeact.getText(),
                    Integer.parseInt(inputcotation.getText()), Integer.parseInt(inputmontantHon.getText()),
                    Integer.parseInt( inputticket.getText()), Integer.parseInt(inputmontantch.getText()),
                      inputcodeconven.getText() );
                   
                           
            
            productService.modifierAssurance(c);
     
     resetTableData(); 
        
        } 
        
        
        
    }

    @FXML
    private void prec(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    
    
}
