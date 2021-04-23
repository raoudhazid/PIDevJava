/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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



//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class FXMLDetailRestauController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label addresse;
    @FXML
    private Label specialite;
  

    
     @FXML
    private Button telecharger;

    String ea;
    String b;
    String c;
    LocalDate m;
  //  ServiceParticipation sp;
   // ServiceRate sr;

    public void setNom(String ea ){
         nom.setText(ea);
        
    }
        public void setAddresse(String b ){
       addresse.setText(b);
        
    }
            public void setSpecialite(String c ){
                specialite.setText(c);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
             
       
        
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
            String value1 = nom.getText();
            
            
            String value2 = addresse.getText();
            String value3 = specialite.getText();
          
            
            String s1 = "";
            s1= s1.concat("    Nom:"     ).concat(value1).concat("         Addresse:     ").concat(value2).concat("      SpecialitÃ©:    ").
                    concat(value3);
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
