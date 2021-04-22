/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Event;
import Entities.Hotel;
import Entities.Monum;
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
import service.ShopService;

/**
 * FXML Controller class
 *
 * @author mon
 */
public class AfficherShopController implements Initializable {

    @FXML
    private TableColumn<Monum, Integer> ida;

    @FXML
    private TableColumn<Monum, Integer> usersid;
     @FXML
    private TableColumn<Monum, String> nom;
      @FXML
    private TableColumn<Monum, String> addresse;
       @FXML
    private TableColumn<Monum, String> etoiles;
        @FXML
    private TableColumn<Monum,String> tel;
            @FXML
    private TableColumn<Monum,String> prix;
    @FXML
    private TableView<Monum> table;
    ShopService sv = new ShopService();

   
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
        ObservableList<Monum> list = FXCollections.observableArrayList();
          try {
            for (Monum bb : sv.afficherFeedback()) {
                list.add(bb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //mettre les données dans la table view:    
        ida.setCellValueFactory(new PropertyValueFactory<>("id"));
       nom.setCellValueFactory(new PropertyValueFactory<>("name"));
           addresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        etoiles.setCellValueFactory(new PropertyValueFactory<>("destination"));
           tel.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("img"));
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
        ObservableList<Monum> SelectedRows, allpeople;

        allpeople = table.getItems();
        // il donne les ligne qui vous avez déja séléctionner
        SelectedRows = table.getSelectionModel().getSelectedItems();

        for (Monum gg : SelectedRows) {
            allpeople.remove(gg);
            sv.supprimerparID(gg.getId());
        }
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AddShop.fxml"));
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
       Monum M = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyShop.fxml"));
     Parent root = loader.load();
     ModifyShopController scene2Controller = loader.getController();

          
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
       Monum M = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDetailShop.fxml"));
     Parent root = loader.load();
     FXMLDetailShopController scene2Controller = loader.getController();

          
                scene2Controller.setName(M.getName());
        scene2Controller.setDestination(M.getDestination());
             
               scene2Controller.setDescription(M.getDescription());
               scene2Controller.setAdresse(M.getAdresse());
                Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hello World");
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

      @FXML
    private void Chercher(ActionEvent event) {
         int text = Integer.parseInt(ch.getText());
         ShopService sv = new  ShopService();
              ObservableList<Monum> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(Monum bb: sv.RechercherFeed(text))
             listu.add(bb);
            table.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(AfficherShopController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AfficherShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
