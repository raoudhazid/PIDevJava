package Controller;

import Entity.File;
import com.jfoenix.controls.JFXTextField;
import helpers.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.File;
import helpers.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddProductImages  implements Initializable {


        // public Button fileChooser;
        public AnchorPane anchorpane;

    @FXML
    private JFXTextField liblField;



        String imagePath = null;




        String query = null;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement;
        File f = null;
        private boolean update;
        int formationId;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }

        public void AddB(javafx.scene.input.MouseEvent mouseEvent) {
            connection = DbConnect.getConnect();
            String id = liblField.getText();






            if (id.isEmpty() ||imagePath== null ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All DATA");
                alert.showAndWait();

            } else {
                getQuery();
                insert();
                CleanB();

            }
        }


        private void getQuery() {



            if (update == false) {

                query = "INSERT INTO `images_product`( `product_id`, `name`) VALUES (?,?)";

            }else{
                query = "UPDATE `images_product` SET "
                        + "`product_id`=?,"
                        +"`name`= ? WHERE id = '"+formationId+"'";
            }

        }

        private void insert() {

            try {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, liblField.getText());
                preparedStatement.setString(2, imagePath);


                preparedStatement.execute();

            } catch (SQLException ex) {
                Logger.getLogger(AddProductImages.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


        public void CleanB() {
            liblField.setText(null);

        }

        void setUpdate(boolean b) {
            this.update = b;

        }


    public void multipleFileChooserAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Multiple file dialog");
        Stage stage = (Stage)anchorpane.getScene().getWindow();

        java.util.List<java.io.File> list=fileChooser.showOpenMultipleDialog(stage);

        if(list != null)
        {
            for(java.io.File file: list)
            {
                Desktop desktop =Desktop.getDesktop();
                try {
                    desktop.open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



        public String handleButtonAction(ActionEvent event) throws IOException {



            FileChooser fc = new FileChooser();


            fc.setInitialDirectory(new java.io.File("C:\\ESPRIT\\Esprit\\helpd\\src\\resources"));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf Files", "*.pdf"));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg Files", "*.jpeg"));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg Files", "*.jpg"));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("png Files", "*.png"));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("video Files", "*.mp4"));

            java.io.File f = fc.showOpenDialog(null);
            if(f != null)
            {
                System.out.println(f);
            }
            imagePath=f.getPath();
            imagePath =imagePath.replace("\\","\\\\");
            return f.getName();


        }
        void setTextField(int id, int product_id, String name) {

            formationId = id;
            liblField.setText(String.valueOf(product_id));
        }

    }




