/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.EventService;
import service.HotelService;

/**
 * FXML Controller class
 *
 * @author mon
 */
public class ModifyHotelController implements Initializable {
   @FXML
    private JFXButton btn_feed;
    @FXML
    private JFXButton btn_sugg;
   @FXML
    private JFXButton btn_chart;
    @FXML
    private JFXButton btn_home;
    @FXML
    private JFXButton logout;
    @FXML
    private TextField nom;
    @FXML
    private TextField addresse;
        @FXML
    private TextField etoiles;
    @FXML
    private TextField tel;
        @FXML
    private TextField prix;
 
   

 
    @FXML
    private Button save;
  @FXML
    private int x;
  
     @FXML
    private int a;
      @FXML
    private int b;


      public void setID(int id) {
        this.x = id;
    }
 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if ((event.getSource() == btn_feed)) {
            try {
                javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/affich.fxml"));
                Scene sceneview = new Scene(tableview);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sceneview);
                window.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (event.getSource() == btn_sugg) {
            try {
                javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("affich.fxml"));
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
        } 
         else if (event.getSource() == btn_chart) {
           try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("affich.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
    }
        
    

    /**
     * This method will allow the user to double click on a cell and update the
     * first name of the person
     */
    @FXML
    private void Enregistrer(ActionEvent event) throws IOException {
       HotelService sv = new HotelService();
        sv.modifierFeed1(x,nom.getText(),addresse.getText(),Integer.parseInt(etoiles.getText()),tel.getText(),prix.getText());
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ModifyHotel.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
   
          String Nom = nom.getText();
        String Addresse = addresse.getText();
          int Etoiles = Integer.parseInt(etoiles.getText());
           String Tel = tel.getText();
        String Prix = prix.getText();
        
    
         if ( Nom.trim().length() < 1 ||Addresse.trim().length() < 1 || Tel.trim().length() < 1  ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Attention");
            alert.setContentText("Verifier les champs");

            alert.showAndWait();
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
                Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
    private void back(ActionEvent event) {
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AffichHotel.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
