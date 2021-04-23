/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Event;
import Entities.Hotel;
import Entities.Restaurant;
import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.EventService;
import service.HotelService;
import service.RestaurantService;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.apache.log4j.BasicConfigurator;
import org.apache.poi.ss.usermodel.CellStyle;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author mon
 */
public class AfficherRestauController implements Initializable {

    @FXML
    private TableColumn<Restaurant, Integer> ida;

    @FXML
    private TableColumn<Restaurant, Integer> userid;
     @FXML
    private TableColumn<Restaurant, String> nom;
      @FXML
    private TableColumn<Restaurant, String> addresse;
       @FXML
    private TableColumn<Restaurant, String> specialite;

    @FXML
    private TableView<Restaurant> table;
    RestaurantService sv = new RestaurantService();
     private Connection con;
 
    private Statement ste;
    @FXML
    private TextField ch;
     @FXML
    private TableColumn<?, ?> id;
    @FXML
    private JFXButton btn_feed;
    @FXML
    private JFXButton btn_sugg;
     @FXML
    private JFXButton btn_chart;

    @FXML
    private JFXButton btn_home;
    @FXML
    private Label names;
    @FXML
    private JFXButton logout;
    @FXML
    private Button Supprimer;
    @FXML
    private Button Ajouter;
    @FXML
    private Button modify;
    
    @FXML
        private Button btnexcel;
   public AfficherRestauController() {
       Connection con= ConnexionBD.getInstance().getCnx();
        BasicConfigurator.configure();
       
    }  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Restaurant> list = FXCollections.observableArrayList();
          try {
            for (Restaurant bb : sv.afficherFeedback()) {
                list.add(bb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRestauController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //mettre les données dans la table view:    
        ida.setCellValueFactory(new PropertyValueFactory<>("id"));
       nom.setCellValueFactory(new PropertyValueFactory<>("nom_r"));
           addresse.setCellValueFactory(new PropertyValueFactory<>("adresse_r"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("specialites"));
     
         userid.setCellValueFactory(new PropertyValueFactory<>("users_id"));
        table.setItems(list);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if ((event.getSource() == btn_feed)) {
            try {
                javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("affich.fxml"));
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
   

   @FXML
    private void Home(MouseEvent event) {
    }
    @FXML
    private void Feedbacks(MouseEvent event) {
    }
    @FXML
    private void Suggestion(MouseEvent event) {
    }
   
       
       

    @FXML
    private void Supprimer(ActionEvent event) {
        ObservableList<Restaurant> SelectedRows, allpeople;

        allpeople = table.getItems();
        // il donne les ligne qui vous avez déja séléctionner
        SelectedRows = table.getSelectionModel().getSelectedItems();

        for (Restaurant gg : SelectedRows) {
            allpeople.remove(gg);
            sv.supprimerparID(gg.getId());
        }
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AddRestau.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  @FXML
    private void modify(ActionEvent event) throws IOException {
       Restaurant M = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyRestau.fxml"));
     Parent root = loader.load();
     ModifyRestauController scene2Controller = loader.getController();

          
            //  scene2Controller.setType(M.getEvent_id());
            //  scene2Controller.setRating(M.getRating());
              scene2Controller.setID(M.getId());
                Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hello World");
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }
 @FXML
    private void show(ActionEvent event) throws IOException {
       Restaurant M = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDetaileRestau.fxml"));
     Parent root = loader.load();
     FXMLDetailRestauController scene2Controller = loader.getController();

          
                scene2Controller.setNom(M.getNom_r());
           scene2Controller.setAddresse(M.getAdresse_r());
              scene2Controller.setSpecialite(M.getSpecialites());
               
                Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hello World");
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }
      @FXML
    private void Chercher(ActionEvent event) {
         int text = Integer.parseInt(ch.getText());
         RestaurantService sv = new  RestaurantService();
              ObservableList<Restaurant> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(Restaurant bb: sv.RechercherFeed(text))
             listu.add(bb);
            table.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(AfficherRestauController.class.getName()).log(Level.SEVERE, null, ex);
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
            } catch (IOException ex) {
                Logger.getLogger(AfficherRestauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   @FXML 
    private void expexl() throws FileNotFoundException, IOException{
        BasicConfigurator.configure();
     ObservableList<Restaurant> list = FXCollections.observableArrayList();
          XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet= wb.createSheet("Restau details");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("id");
        header.createCell(1).setCellValue("users_id");
        header.setHeightInPoints(20);
        
        header.createCell(2).setCellValue("Nom_r");
        header.createCell(3).setCellValue("adresse_r");
        header.createCell(4).setCellValue("specialites");
         int index = 1;
          try {
            for (Restaurant bb : sv.afficherFeedback()) {
                XSSFRow row = sheet.createRow(index);
               row.createCell(0).setCellValue(bb.getId());
               
         row.createCell(1).setCellValue(bb.getUsers_id());
        row.createCell(2).setCellValue(bb.getNom_r());
        row.createCell(3).setCellValue(bb.getAdresse_r());
        row.createCell(4).setCellValue(bb.getSpecialites());
        
        index++;
            }
              
    
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRestauController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
       
        try (FileOutputStream fileOut = new FileOutputStream("RestauDetails.xlsx")) {
            wb.write(fileOut);
        }
        
    }
        
    
}
