/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Event;
import Entities.Participation;
import Entities.Users;
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
import javax.mail.MessagingException;
import service.EventService;
import service.ParticipationService;

/**
 * FXML Controller class
 *
 * @author mon
 */
public class AfficherEventController implements Initializable {

    @FXML
    private TableColumn<Event, Integer> Event_id;

    @FXML
    private TableColumn<Event, Integer> Rating;
     @FXML
    private TableColumn<Event, String> Titre;
      @FXML
    private TableColumn<Event, String> Type;
       @FXML
    private TableColumn<Event, String> Description;
        @FXML
    private TableColumn<Event,LocalDate> Date;
    @FXML
    private TableView<Event> table;
    EventService sv = new EventService();

   
    @FXML
    private TextField ch;
     @FXML
    private TableColumn<?, ?> id;
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
    private JFXButton logout;
     
      @FXML
    private JFXButton btn_rec;
        @FXML
    private JFXButton btn_stat;
   
    @FXML
    private Button Supprimer;
    @FXML
    private Button Ajouter;
    @FXML
    private Button modify;
 Event e;
     public void setEvent(Event e){
        this.e=e;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Event> list = FXCollections.observableArrayList();
          try {
            for (Event bb : sv.afficherFeedback()) {
                list.add(bb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //mettre les données dans la table view:    
        Event_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Rating.setCellValueFactory(new PropertyValueFactory<>("users_id"));
           Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
           Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
        table.setItems(list);
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
        else if (event.getSource() == btn_home) {
           try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("back.fxml"));
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
        ObservableList<Event> SelectedRows, allpeople;

        allpeople = table.getItems();
        // il donne les ligne qui vous avez déja séléctionner
        SelectedRows = table.getSelectionModel().getSelectedItems();

        for (Event gg : SelectedRows) {
            allpeople.remove(gg);
            sv.supprimerparID(gg.getId());
        }
    }
     @FXML
    private void AjouterP(ActionEvent event) throws MessagingException {
        Event M = table.getSelectionModel().getSelectedItem();
         Users u =new Users();
          u.setId(34);
          
             ParticipationService ms = new ParticipationService();
        // il donne les ligne qui vous avez déja séléctionner
     

      
           // ms.ajouterP(u,e);
            ObservableList<Event> SelectedRows, allpeople;
             allpeople = table.getItems();
            
          

       
        // il donne les ligne qui vous avez déja séléctionner
        SelectedRows = table.getSelectionModel().getSelectedItems();

        for (Event gg : SelectedRows) {
            System.out.println(gg.getId());
           ms.ajouterP(34,gg.getId());
           
        } 
        
    }

    @FXML
    private void Ajouter(ActionEvent event) {
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
    
  @FXML
    private void modify(ActionEvent event) throws IOException {
       Event M = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyEvent.fxml"));
     Parent root = loader.load();
     ModifyEventController scene2Controller = loader.getController();

          
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
       Event M = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDetail.fxml"));
     Parent root = loader.load();
     FXMLDetailController scene2Controller = loader.getController();

          
                scene2Controller.setType(M.getType());
           scene2Controller.setTitre(M.getTitre());
              scene2Controller.setDescription(M.getDescription());
               scene2Controller.setDate(M.getDate_event());
                Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hello World");
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

      @FXML
    private void Chercher(ActionEvent event) {
         String text = ch.getText();
         EventService sv = new  EventService();
              ObservableList<Event> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(Event bb: sv.RechercherFeed(text))
             listu.add(bb);
            table.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
