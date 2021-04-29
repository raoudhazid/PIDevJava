package Controller;


import Entity.Voiture;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
//import helpers.javaMail;
import helpers.javaMail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Service.ServiceVoiture;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlateformeUI implements Initializable {
    public FontAwesomeIconView adminPage;
    @FXML
    private JFXTextField txtfieldObjet;

     /*******************************************************/

     /******************************************************/

    @FXML
    private Button buttonMusic;
    @FXML
    private JFXTextField adresse_txt;
    @FXML
    private JFXTextArea description_txt;



    @FXML
    private Button play;
    @FXML
    private Button goToHome;

    @FXML
    private HBox hbox,hboxfirst ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Voiture> ls=new ArrayList<Voiture>();
        List<Voiture> lss=new ArrayList<>();
        ServiceVoiture s=new ServiceVoiture();
        try {
            ls= s.read();
            lss=s.read();
            try {
                for (int i = 0; i < ls.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/item.fxml"));
                    HBox cardBox = fxmlLoader.load();
                    Controller.ItemControllor itemControllor = fxmlLoader.getController();
                    itemControllor.setData(ls.get(i));
                    hbox.getChildren().add(cardBox);
                    hbox.setPrefWidth(409 * i);
                }
                for(int j=0;j<lss.size();j++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/item.fxml"));
                    HBox cardBox = fxmlLoader.load();
                    Controller.ItemControllor itemControllor = fxmlLoader.getController();
                    itemControllor.setData(ls.get(j));
                    hboxfirst.getChildren().add(cardBox);
                    hboxfirst.setPrefWidth(409 * j);

                }




            } catch (IOException ex) {
                Logger.getLogger(PlateformeUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void mailfonction(MouseEvent event) {
        try {
            String Object = adresse_txt.getText();
            String Corps = adresse_txt.getText();
            javaMail.sendMail("mohamedhabib.khattat@esprit.tn", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void playMusic(String filepath){
        InputStream music;
        try {
            music = new FileInputStream(new File(filepath));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error");
        }
    }

    public void btnHome (ActionEvent event) {
        playMusic("C:\\Users\\Mohamed\\pidevjava\\src\\resources\\Sound.wav");

    }




}
