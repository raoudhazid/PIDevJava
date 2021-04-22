/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Monum;


import com.jfoenix.controls.JFXButton;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.controlsfx.control.Rating;



//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class FXMLDetailShopController implements Initializable {

       @FXML
    private JFXButton btn_rec;
     @FXML
    private JFXButton btn_event;
       @FXML
    private JFXButton btn_hotel;
     @FXML
    private JFXButton btn_voyage;
       @FXML
    private JFXButton btn_shop;
     @FXML
     private JFXButton btn_home;
    @FXML
    private Label name;
   
  
    @FXML
    private Label destination;
  
   @FXML
    private Label description;
    @FXML
    private Label adresse;
 

     @FXML
    private Button telecharger;
    Monum e;
    String ea;
    String b;
    String c;
   String m;
  //  ServiceParticipation sp;
   // ServiceRate sr;
     
    public void setName(String ea ){
         name.setText(ea);
        
    }
        public void setDescription(String b ){
       description.setText(b);
        
    }
         
                public void setDestination(String m ){
                    destination.setText(m);
     
        
    }
                    public void setAdresse(String c ){
                    adresse.setText(c);
     
        
    }
    public void setMonum(Monum e){
        this.e=e;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
             
       

    }

   
      @FXML
    private void handleButtonAction(ActionEvent event) {
         if ((event.getSource() == btn_hotel) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("affich.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
     } else if (event.getSource() == btn_voyage) {
            try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AffichHotel.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
         } else if (event.getSource() == btn_event) {
           try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AffichVoyage.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
      else if (event.getSource() == btn_shop) {
           try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AffichShop.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
          else if (event.getSource() == btn_rec) {
           try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("affichR.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
         else if (event.getSource() == btn_rec) {
           try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("statistiqueInscriptions.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
       
    } 

     
   
    @FXML
    private void retour(ActionEvent event) throws IOException {
        
          Stage stage = new Stage();
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("back.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
  
     @FXML
    private void savetoword(ActionEvent event) throws FileNotFoundException, IOException {
        
         XWPFDocument document;
        document = new XWPFDocument();
        try (FileOutputStream out = new FileOutputStream(new File("demo.docx"))) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            String value1 = name.getText();
            
            
            String value2 = destination.getText();
           
            String value3 = description.getText();
             String value4 = adresse.getText();
            
            String s1 = "";
            s1= s1.concat("    Name:"     ).concat(value1).concat("         Destination:     ").concat(value2).concat("         Description:     ").concat(value3).concat("         Adresse:     ").concat(value4);
            run.setText(s1);
            document.write(out);
        }
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("DOCUMENT ENREGISTRE");
                
                alert.showAndWait();
    }

}
