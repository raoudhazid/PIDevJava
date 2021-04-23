package Controller;

import Entity.Product;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import helpers.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ADDProductController implements Initializable {

    @FXML
    private JFXTextField title_txt;

    @FXML
    private JFXTextArea description_txt;

    @FXML
    private JFXTextField qte_txt;

    @FXML
    private JFXTextField price_txt;

    @FXML
    private JFXTextField categ_txt;

    @FXML
    private JFXTextField dis_txt;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Product f = null;
    private boolean update;
    int productId;


    @FXML
    public void AddButton(javafx.scene.input.MouseEvent mouseEvent) {
        connection = DbConnect.getConnect();
        String title = title_txt.getText();
        String description=description_txt.getText();
        String qte=qte_txt.getText();
        String price=price_txt.getText();
        String Category_id=categ_txt.getText();
        String disable=dis_txt.getText();






        if (title.isEmpty() || description.isEmpty()
                || qte.isEmpty()||price.isEmpty()
                ||Category_id.isEmpty()
                ||disable.isEmpty()   ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            ClearButton();

        }
    }


    public void ClearButton() {
        title_txt.setText(null);
        description_txt.setText(null);
        qte_txt.setText(null);
        price_txt.setText(null);
        categ_txt.setText(null);
        dis_txt.setText(null);
    }
    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title_txt.getText());
            preparedStatement.setString(2, description_txt.getText());
            preparedStatement.setString(3, qte_txt.getText());
            preparedStatement.setString(4, price_txt.getText());
            preparedStatement.setString(5, categ_txt.getText());
            preparedStatement.setString(6, dis_txt.getText());




            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ADDProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    private void getQuery() {



        if (update == false) {

            query = "INSERT INTO `product`( `title`, `description`, `qte`, `price`, `category_id`, `disable`) VALUES (?,?,?,?,?,?)";

        }else{
            query = "UPDATE `product` SET "
                    + "`title`=?,"
                    + "`description`=?,"
                    + "`qte`= ?,"
                    + "`price`= ?,"
                    + "`category_id`= ?,"
                    +"`disable`= ? WHERE id = '"+productId+"'";
        }

    }


    void setUpdate(boolean b) {
        this.update = b;

    }
    void setTextField(int id, String title, String description
            , String qte, String price, String category_id,String disable) {

        productId = id;
        title_txt.setText(title);
        description_txt.setText(description);
        qte_txt.setText(qte);
        price_txt.setText(price);
        qte_txt.setText(category_id);
        qte_txt.setText(disable);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
