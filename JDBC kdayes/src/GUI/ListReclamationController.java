/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entities.Reclamation;

import Services.ReclamationService;

import com.itextpdf.text.DocumentException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Hyperlink;
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
public class ListReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> contenu;
    @FXML
    private TableColumn<?, ?> user;
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
public ObservableList<Reclamation> list;
public static Reclamation connectedRec;
    ReclamationService cs = new ReclamationService();
    @FXML
    private Button AFF;
    @FXML
    private Button pdf2;
    @FXML
    private Hyperlink assurance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    ReclamationService pss = new ReclamationService();
        ArrayList<Reclamation> c = new ArrayList<>();
        try {
            c = (ArrayList<Reclamation>) pss.AfficherAllReclamations();
        } catch (SQLException ex) {
            System.out.println("erreurrrrrrrrrrrrr");
        }
        ObservableList<Reclamation> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
 user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
             
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllReclamations()
            );      
            FilteredList<Reclamation> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Reclamation>) Reclamations -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Reclamations.getContenu().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
       
      
    }

    @FXML
    private void supp(ActionEvent event) throws SQLException {
         if (event.getSource() == supp) {
            Reclamation rec = new Reclamation();
            rec.setId(tableview.getSelectionModel().getSelectedItem().getId());  
            ReclamationService cs = new ReclamationService();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Are you sure you want to delete this reclamation ");
            a.setHeaderText(null);
            a.showAndWait();
            cs.SupprimerReclamation(rec);
            resetTableData(); 
          TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Supprimé une reclamation avec succées");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        
        
        
    }


    
    
}
  public void resetTableData() throws SQLDataException, SQLException {

        List<Reclamation> listrec = new ArrayList<>();
        listrec = cs.AfficherAllReclamations();
        ObservableList<Reclamation> data = FXCollections.observableArrayList(listrec);
        tableview.setItems(data);
    }

    @FXML
    private void Modif(ActionEvent event) throws IOException {
        
     
           ReclamationService ps = new ReclamationService();
        Reclamation c = new Reclamation(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getUser_id(),
                 tableview.getSelectionModel().getSelectedItem().getDate(),
                tableview.getSelectionModel().getSelectedItem().getType(),
               tableview.getSelectionModel().getSelectedItem().getContenu(),
                tableview.getSelectionModel().getSelectedItem().getImg()
                );
        ListReclamationController.connectedRec = c;
        
             Parent page1 = FXMLLoader.load(getClass().getResource("ModifierReclamation.fxml"));
        Scene scene = new Scene(page1, 1144, 741);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        
            Parent page1 = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Ajouter une Reclamations");
        stage.setScene(scene);
        stage.show();
        
    }

 @FXML
    private void AFF(ActionEvent event) throws SQLException {
        
        AfficheParDateTrié();
        
        
    }
     public void AfficheParDateTrié() throws SQLDataException, SQLException {

        List<Reclamation> listevents = new ArrayList<>();
        listevents = cs.AfficherAllReclamationTriéParDate();
        ObservableList<Reclamation> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }  

  private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\Administrateur.DESKTOP-BMBSNE9\\Desktop\\ListReclamation.pdf"));
        d.open();
        d.add(new Paragraph("Liste des Reclamations"));
        
        PdfPTable pTable = new PdfPTable(1);
       

     
        
        tableview.getItems().forEach((t) -> {
            pTable.addCell(String.valueOf(t.getContenu()));
  
            
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                Logger.getLogger(ListReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        d.close();
        Desktop.getDesktop().open(new File("C:\\Users\\Administrateur.DESKTOP-BMBSNE9\\Desktop\\ListReclamation.pdf"));

    } 
    
    
    
    
    
    @FXML
    private void pdf2(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
         if (event.getSource() == pdf2) {
            
             printPDF();
            
    
        } 
 

}

    @FXML
    private void assurance(ActionEvent event) throws IOException {
        
                 Parent page1 = FXMLLoader.load(getClass().getResource("AjouterAssurance.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }


}