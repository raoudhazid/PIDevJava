package Controller;

import Entity.Product;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableViewsFile2 implements Initializable {


    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Product formation = null ;
/********************//***********************/
    //  int index = -1;

/********************//***********************/

    ObservableList<Product> formationList = FXCollections.observableArrayList();
    @FXML
    private Button addFileB;

    @FXML
    private TableView<Product> TableProduit;

    @FXML
    private TableColumn<Product, String> idCol;

    @FXML
    private TableColumn<Product, String> titleCol;

    @FXML
    private TableColumn<Product, String> DescriptionCol;

    @FXML
    private TableColumn<Product, String> qteCol;

    @FXML
    private TableColumn<Product, String> priceCol;

    @FXML
    private TableColumn<Product, String> CategIdCol;

    @FXML
    private TableColumn<Product, String> DisableCol;

    @FXML
    private TableColumn<Product, String> editCol;
    @FXML
    private TextField filterfield;

    @FXML
    private Button buttonHD;

    @FXML
    void Print(MouseEvent event) {

    }

    @FXML
    void addFile(ActionEvent event) {

    }

    public void close(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }



    @FXML
    public void getAddView(javafx.scene.input.MouseEvent mouseEvent)throws Exception {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/ADDProduct.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void helpdeslk(ActionEvent event) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/plateformeUI.fxml"));

        Stage window =(Stage) buttonHD.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));

    }

    @FXML
    void print(MouseEvent event) {

    }

    @FXML
    void refresh() {
        try {
            formationList.clear();




            query = "SELECT * FROM `product`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                formationList.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("qte"),
                        resultSet.getString("price"),
                        resultSet.getString("category_id"),
                        resultSet.getString("disable")

                ));
                TableProduit.setItems(formationList);



            }


        } catch (SQLException ex) {
            Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void loadDate() {

        connection = DbConnect.getConnect();
        refresh();


        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        qteCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        CategIdCol.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        DisableCol.setCellValueFactory(new PropertyValueFactory<>("disable"));




        /*this*/ Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFoctory = (TableColumn<Product, String> param) -> {
            // make cell containing buttons
            final TableCell<Product, String> cell = new TableCell<Product, String>() {
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
                                formation = TableProduit.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `Product` WHERE id  ="+formation.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refresh();

                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
                            }





                        });
                        editIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {

                            /*file*/     formation = TableProduit.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui/ADDProduct.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            ADDProductController addProductController = loader.getController();
                            addProductController.setUpdate(true); //addcon
                            addProductController.setTextField(formation.getId(),formation.getTitle(),
                                    formation.getDescription(),
                                    formation.getQte(),formation.getPrice(),
                                    formation.getCategory_id(),formation.getDisable());
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
        TableProduit.setItems(formationList);

    }



    @FXML
    void search(KeyEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadDate();
    }
}
