package Controller;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Entity.*;
import helpers.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.sql.*;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageProductTable implements Initializable {

    @FXML
    private TableView<File> TableFormation;

    @FXML
    private TableColumn<File, String> idFile;//id_file

    @FXML
    private TableColumn<File, String> id_formation;//id



    @FXML
    private TableColumn<File, String> filepath;
    @FXML
    private TableColumn<File, String> editCol;

    @FXML
    private Button buttonHD;
    @FXML
    private Button back_button;
    @FXML
    private Button addFileB;

    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    File formation = null ;
/********************//***********************/
    //  int index = -1;

/********************//***********************/

    ObservableList<File> formationList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();

    }








    /***********************************************REFRECH*************************************************************/
    @FXML
    void refresh() {
        try {
            formationList.clear();

            query = "SELECT * FROM `images_product`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                formationList.add(new File(
                        resultSet.getInt("id"),
                        resultSet.getInt("product_id"),
                        resultSet.getString("name")


                ));
                TableFormation.setItems(formationList);



            }


        } catch (SQLException ex) {
            Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    /***********************************************REFRECH*************************************************************/




    private void loadDate() {

        connection = DbConnect.getConnect();
        refresh();


        idFile.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_formation.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        filepath.setCellValueFactory(new PropertyValueFactory<>("name"));


        /*this*/ Callback<TableColumn<File, String>, TableCell<File, String>> cellFoctory = (TableColumn<File, String> param) -> {
            // make cell containing buttons
            final TableCell<File, String> cell = new TableCell<File, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {

                            try {
                                formation = TableFormation.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `images_product` WHERE id  ="+formation.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refresh();

                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
                            }





                        });
                        editIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {

                            /*file*/     formation = TableFormation.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui/addProductImages.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddProductImages addFileController = loader.getController();
                            addFileController.setUpdate(true);
                            addFileController.setTextField(formation.getId(), formation.getProduct_id(),
                                    formation.getName());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();




                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editCol.setCellFactory(cellFoctory);
        TableFormation.setItems(formationList);
    }
    /******************************************************************************************/
    public void close(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    /*********************************************************************************************/
//ci dessous pour faire le call ADD scene
    public void getAddView(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/addProductImages.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*********************************************************************************************************/
    public void print(javafx.scene.input.MouseEvent mouseEvent) {
    }
    /*************************************************************************************************/
    public void addFile() throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/ProductUI.fxml"));

        Stage window =(Stage) addFileB.getScene().getWindow();
        window.setScene(new Scene(root, 1500, 1300));
    }
    public void back_action() throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/TableViewFile2.fxml"));

        Stage window =(Stage) back_button.getScene().getWindow();
        window.setScene(new Scene(root, 880, 541));
    }

    public void Voiture() throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/voiture.fxml"));

        Stage window =(Stage) buttonHD.getScene().getWindow();
        window.setScene(new Scene(root, 1140, 590));
    }

/*******************************************************************************************************/


}