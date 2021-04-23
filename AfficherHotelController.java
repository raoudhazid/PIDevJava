/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Event;
import Entities.Hotel;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import service.EventService;
import service.HotelService;

/**
 * FXML Controller class
 *
 * @author mon
 */
public class AfficherHotelController implements Initializable {

    @FXML
    private TableColumn<Hotel, Integer> ida;

    @FXML
    private TableColumn<Hotel, Integer> usersid;
     @FXML
    private TableColumn<Hotel, String> nom;
      @FXML
    private TableColumn<Hotel, String> addresse;
       @FXML
    private TableColumn<Hotel, Integer> etoiles;
        @FXML
    private TableColumn<Hotel,String> tel;
            @FXML
    private TableColumn<Hotel,String> prix;
    @FXML
    private TableView<Hotel> table;
    HotelService sv = new HotelService();

   
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

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Hotel> list = FXCollections.observableArrayList();
          try {
            for (Hotel bb : sv.afficherFeedback()) {
                list.add(bb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //mettre les données dans la table view:    
        ida.setCellValueFactory(new PropertyValueFactory<>("id"));
       nom.setCellValueFactory(new PropertyValueFactory<>("nom_h"));
           addresse.setCellValueFactory(new PropertyValueFactory<>("adresse_h"));
        etoiles.setCellValueFactory(new PropertyValueFactory<>("etoiles_h"));
           tel.setCellValueFactory(new PropertyValueFactory<>("numtlf_h"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix_h"));
         usersid.setCellValueFactory(new PropertyValueFactory<>("users_id"));
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
        ObservableList<Hotel> SelectedRows, allpeople;

        allpeople = table.getItems();
        // il donne les ligne qui vous avez déja séléctionner
        SelectedRows = table.getSelectionModel().getSelectedItems();

        for (Hotel gg : SelectedRows) {
            allpeople.remove(gg);
            sv.supprimerparID(gg.getId());
        }
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AddHotel.fxml"));
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
       Hotel M = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyHotel.fxml"));
     Parent root = loader.load();
     ModifyHotelController scene2Controller = loader.getController();

          
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
    private void Chercher(ActionEvent event) {
         int text = Integer.parseInt(ch.getText());
         HotelService sv = new  HotelService();
              ObservableList<Hotel> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(Hotel bb: sv.RechercherFeed(text))
             listu.add(bb);
            table.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(AfficherHotelController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AfficherHotelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
