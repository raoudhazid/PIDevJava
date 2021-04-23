/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Event;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
//import Services.ServiceParticipation;

import com.google.zxing.qrcode.QRCodeWriter;
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



//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class FXMLDetailController implements Initializable {
 @FXML
    private JFXButton btn_rec;
  @FXML
    private JFXButton btn_stat;
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
    private Label variete;
    @FXML
    private Label type;
    @FXML
    private Label description;
  
    @FXML
    private Label date;
  
   @FXML
    private Label titre;
 
     @FXML
    private ImageView im_qrcode;
     @FXML
    private Button telecharger;
    Event e;
    String ea;
    String b;
    String c;
    LocalDate m;
  //  ServiceParticipation sp;
   // ServiceRate sr;
      private String diretorio;
    private static final String DIR = "QRDir";
    public void setType(String ea ){
         type.setText(ea);
        
    }
        public void setTitre(String b ){
       titre.setText(b);
        
    }
            public void setDescription(String c ){
                description.setText(c);
        
    }
                public void setDate(LocalDate m ){
                    date.setText(m.toString());
     
        
    }
    public void setEvent(Event e){
        this.e=e;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
             
       
           diretorio = new File("").getAbsolutePath();
        diretorio += File.separator + DIR;
        File file = new File(diretorio);
        if (!file.isDirectory()) {
            file.mkdir();
        }
         
                 
       // variete.setText(e.getVariete());
   
//        date.setText(this.m.toString());
       // int value=sr.getValueRate(e, u);
      //  rate.setRating(Double.parseDouble(String.valueOf(value)));
      //  File newFile = new File("C:\\Users\\azizm\\Desktop\\3A\\java\\pepiniere\\src\\Images\\"+e.getImage());
        

      
             //afficher qrcode
       String nom = type.getText();
            try {
                String contenuQR = nom;

               

                FileOutputStream fout = new FileOutputStream(diretorio + File.separator + nom + ".png");
                ByteArrayOutputStream bos = QRCode.from(contenuQR).withSize(125, 125).to(ImageType.PNG).stream();

                fout.write(bos.toByteArray());

                bos.close();
                fout.close();
                fout.flush();
                Image image = new Image(new FileInputStream(diretorio + File.separator + nom + ".png"));

                im_qrcode.setImage(image);
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            Logger.getLogger(FXMLDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
         else if (event.getSource() == btn_stat) {
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
    private void gerarQRcode(ActionEvent event) throws IOException {
        String nom = type.getText();
            try {
                String contenuQR = nom;

               

                FileOutputStream fout = new FileOutputStream(diretorio + File.separator + nom + ".png");
                ByteArrayOutputStream bos = QRCode.from(contenuQR).withSize(125, 125).to(ImageType.PNG).stream();

                fout.write(bos.toByteArray());

                bos.close();
                fout.close();
                fout.flush();
                Image image = new Image(new FileInputStream(diretorio + File.separator + nom + ".png"));

                im_qrcode.setImage(image);
            } catch (FileNotFoundException ex) {
            }
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
            String value1 = type.getText();
            
            
            String value2 = titre.getText();
            String value3 = description.getText();
            String value4 = date.getText();
            
            String s1 = "";
            s1= s1.concat("    Type:"     ).concat(value1).concat("         Titre:     ").concat(value2).concat("      Description:    ").
                    concat(value3).concat("         Date:     ").concat(value4);
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
