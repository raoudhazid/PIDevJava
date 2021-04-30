/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Blog;
import Entities.Users;
import Entities.Rate;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.jfoenix.controls.JFXButton;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

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
import service.RatingService;

/**
 *
 * @author Lenovo
 */
public class FXMLDetailBlogController implements Initializable{
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
    private Label sujet;
   
  
    @FXML
    private Label destination;
  
   @FXML
    private Label description;
   @FXML
    private Label date;
    @FXML
    private Label ratin;
    @FXML
    private Rating rate;
 

     @FXML
    private Button telecharger;
    Blog e;
    String ea;
    String b;
    String c;
   LocalDate m;
   RatingService sr;
   int r;
   
   
   public void setSujet(String ea ){
         sujet.setText(ea);
        
    }
   
    public void setDescription(String c ){
       description.setText(c);
        
    }
         
                public void setDestination(String b ){
                    destination.setText(b);
     
        
    }
                /* public void setDate(String m ){
       description.setText(m);
        
    */
         
              /*  public void setRatin(String r ){
                    /*Users u =new Users();
                    int value=sr.getValueRate(e, u);
        rate.setRating(Double.parseDouble(String.valueOf(value)));*/
                  /*  ratin.setText(r);
     
        
    }
                */
                public void setBlog(Blog e){
        this.e=e;
        
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        //To change body of generated methods, choose Tools | Templates.
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
        try (FileOutputStream out = new FileOutputStream(new File("Blog.docx"))) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            String value1 = sujet.getText();
            
            
            String value2 = destination.getText();
           
            String value3 = description.getText();
            // String value4 = date.getText();
             //String value5 = ratin.getText();
            
            String s1 = "";
            s1= s1.concat("    Sujet:"     ).concat(value1).concat("         Destination:     ").concat(value2).concat("         Description:     ");
            run.setText(s1);
            document.write(out);
        }
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("DOCUMENT ENREGISTRE");
                
                alert.showAndWait();
                
                
    }
    
    
   /* @FXML
    private void rate(ActionEvent event)throws IOException
    {
        Users u=new Users();
        u.setId(4);
     
        
        
        if (rate.getRating() == 0.0){
              String titleerr = "Saisir une note avant de clicker sur le boutton";
                String messagerr = "note ne doit pas etre zero";

                TrayNotification tray = new TrayNotification();
                tray.setTitle(titleerr);
                tray.setMessage(messagerr);
               
                tray.showAndWait();
        }
        else {
               double valeur =rate.getRating();
        int val=(int)valeur;
        System.out.println("val"+val);
        if(sr.getValueRate(e, u)==0)
        sr.addRate(e, u, val);
        else
            sr.updateRate(e, u, val);
           String titleerr = "note ajouté";
                String messagerr = "evalué avec succées";

                TrayNotification tray = new TrayNotification();
                tray.setTitle(titleerr);
                tray.setMessage(messagerr);
                
                tray.showAndWait();
                
                   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDetail.fxml"));     
                            
                            Parent root;          
                            try {
                                Stage stage = new Stage();
                                                ((Node)(event.getSource())).getScene().getWindow().hide();
                                root = (Parent)fxmlLoader.load();
                                FXMLDetailBlogController controller = fxmlLoader.<FXMLDetailBlogController>getController();
                                controller.setBlog(e);
                                Scene scene = new Scene(root); 
                                
                                stage.setScene(scene);    

                                stage.show(); 
                            } catch (IOException ex) {
                                
                            }
        
        }
      
        
       
    }*/

                
   
   
}
