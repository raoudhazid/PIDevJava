/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author mon
 */
public class StatistiqueInscriptionController implements Initializable {


       @FXML
    private JFXButton btn_feed;
       @FXML
    private JFXButton btn_sugg;
     @FXML
    private JFXButton btn_trans;
     @FXML
     private JFXButton btn_home;
        @FXML
     private JFXButton btn_chart;
 
    @FXML
    private PieChart pieChart;
 
Connection cnx; 
    Statement ste ; 
    ObservableList<PieChart.Data> piechartdata; 
    ArrayList <String> p = new ArrayList<> (); 
    ArrayList<Integer> n = new ArrayList<> (); 
        ObservableList<String> liste_profil = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadData();
       pieChart.setData(piechartdata); 
    }    
    public void loadData() {
        String req = "Select event.type , COUNT(event.type) as nb   from  event group by  event.type "; 
        
        piechartdata = FXCollections.observableArrayList(); 
        
        cnx = ConnexionBD.getInstance().getCnx(); 
        
        try { 
            ResultSet rs = cnx.createStatement().executeQuery(req);
            while(rs.next()) {
                piechartdata.add(new PieChart.Data(rs.getString("type")+"("+Integer.toString(rs.getInt("nb"))+")",
                        rs.getInt("nb")));
                String nombre = Integer.toString(rs.getInt("nb")); 
                p.add(rs.getString("type")+nombre); 
                n.add(rs.getInt("nb")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
      
    }    
      @FXML
    private void handleButtonAction(ActionEvent event) {
         if ((event.getSource() == btn_feed) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("affich.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
     } else if (event.getSource() == btn_sugg) {
            try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("affichR.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
         } else if (event.getSource() == btn_home) {
           try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("back.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
         else if (event.getSource() == btn_chart) {
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
    private void back(ActionEvent event) {
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
                Logger.getLogger(StatistiqueInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    
}
