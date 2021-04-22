/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.Monument;
import Entities.Hotel;
import Entities.Monum;
import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import service.EventService;
import service.HotelService;
import service.ShopService;
import service.MonumentService;

/**
 *
 * @author Lenovo
 */
public class AddMonumentController implements Initializable {
    
    @FXML
    private JFXButton btn_feed;
    @FXML
    private JFXButton btn_sugg;
    @FXML
    private JFXButton btn_home;
     @FXML
    private JFXButton btn_chart;

    @FXML
    private JFXButton logout;

    @FXML
    private TextField name;
    @FXML
    private TextField adresse;
    @FXML
    private TextField description;
      @FXML
    private TextField destination;
        @FXML
    private TextField img;
        @FXML
    private TextField prix;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
         // TODO
    }
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if ((event.getSource() == btn_feed)) {
            try {
                javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));
                Scene sceneview = new Scene(tableview);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sceneview);
                window.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else if (event.getSource() == btn_home) {
            try {
                javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("back.fxml"));
                Scene sceneview = new Scene(tableview);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sceneview);
                window.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (event.getSource() == btn_sugg) {
            try {
                javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));
                Scene sceneview = new Scene(tableview);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sceneview);
                window.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
         else if (event.getSource() == btn_chart) {
           try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
    }
    @FXML
    private void Home(MouseEvent event) {
    }
    
    @FXML
    private void ajouter(ActionEvent event) throws IOException, SQLException {

        String Name = name.getText();
          
         
      String Destination = destination.getText();
      String Adresse = adresse.getText();
          String Description = description.getText();
          String Img = img.getText();
          int Prix= Integer.parseInt(prix.getText());
         
       Monument t = new Monument(Name, Destination,Adresse,Description,Prix,Img);
       MonumentService ms = new MonumentService();
        ms.ajouterFeed(t);
              Image img = new Image("images/success.png");
        Notifications notificationbuilder = Notifications.create()
                   .title("Add Monument'")
                   .text("Successful insertion!")
                   .graphic(new ImageView (img) )
                   .hideAfter(Duration.seconds(5))
                   .position(Pos.BOTTOM_LEFT)
                   .onAction(new EventHandler<ActionEvent> (){
                      @Override 
                       public void handle(ActionEvent event){
                         System.out.println("Notification");
                       }
                       
                   });
                 notificationbuilder.darkStyle();
                notificationbuilder.showConfirm();
                 try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AffichMonument.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void back(ActionEvent event) {
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AffichMonument.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void logoutClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you wish to exit?", ButtonType.YES,
                ButtonType.NO);
        alert.setTitle("Exit Program");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            try {
                Stage stage;
                Parent indexPage = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
                Scene scene = new Scene(indexPage);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(AddShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
        
  

    

   
}
