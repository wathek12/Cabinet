/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Services.ReclamationService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private Button A;
    @FXML
    private TextField Contenu;
    @FXML
    private Hyperlink prec;
    @FXML
    private ComboBox<String> Type;
    @FXML
    private ComboBox<Integer> usertype;
Connection cnx;
    @FXML
    private Button Timage;
    @FXML
    private ImageView imgajoutincours;
    @FXML
    private Label imgpathttt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
        ObservableList<String> list_ne = FXCollections.observableArrayList("User", "Publication");
        Type.setItems(list_ne);
          ObservableList<Integer> ll = FXCollections.observableArrayList(1,2,3,4,5,6,7);
        usertype.setItems(ll);
        /*  try {
            String req = "select * from user";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                usertype.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        
         
    }    

    @FXML
    private void insert(ActionEvent event) throws SQLException, IOException, MessagingException {
          ReclamationService productService = new ReclamationService();
  
        if (Contenu.getText().equals("")
              ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("You cant send a reclamation without content ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (Contenu.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
              ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        else{
            
        
             Date date = new Date(System.currentTimeMillis());
 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
            Reclamation c = new Reclamation(usertype.getValue(),sqlDate2,
                   
                  Type.getValue(), Contenu.getText(), Timage.getText()
                            );
            productService.ajouterReclamation(c);
              TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Votre reclamation a été envoyé avec succées");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
                  
            Parent page1 = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
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
    
    
 public static void sendMail(String recipient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "outwithus10@gmail.com";
        String password = "out with us5683@";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }  
   
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Votre reclamation a été envoyé avec succés");
            message.setText("Votre reclamation a été envoyé avec succés");
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
   @FXML
    private void addimgcours(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpathttt.setText(file.getAbsolutePath());
    }
    
}
