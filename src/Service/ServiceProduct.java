package Service;
import Entity.Product;
import Entity.Voiture;
import Entity.productFile;
import IntService.IService;
import helpers.DbConnect;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceProduct implements IService<Product> {
    Connection cnx;
    public  ServiceProduct() {
        cnx = DbConnect.getConnect();
    }


    /***************************************************-ADD-************************************************************************/

    @Override
    public void add(Product t) throws SQLException {

        Statement st = cnx.createStatement();
        String query = "insert into voiture (id,title,description,qte,price,category_id,disable)values(NULL, '" + t.getTitle() + "', '" + t.getDescription() + "', '" +t.getQte()+ "', '" + t.getPrice() +"','"+t.getCategory_id()+ "','" + t.getDisable() + "')";
        //"INSERT INTO `tactor` (`id`, `name`, `born`, `description`, `image`) VALUES (NULL, '"+t.getName()+"', '"+ t.getBorn() +"', '"+t.getDescription() +"','"+t.getImage()+"');";
        st.executeUpdate(query);

    }
    /***************************************************-READ-************************************************************************/

    @Override
    public List<Product> read() throws SQLException {
        List<Product> ls = new ArrayList<Product>();
        Statement st = cnx.createStatement();
        String req = "select * from product order by id";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String title = rs.getString("title");
            //   LocalDate born = rs.getDate("born").toLocalDate();
            String description = rs.getString("description");
            String qte = rs.getString("qte");
            String price = rs.getString("price");
            String category_id = rs.getString("category_id");
            String disable = rs.getString("disable");
            Product p = new Product(id,title,description,qte,price,category_id,disable);
            ls.add(p);
        }


        return ls;



    }
    /***************************************************-UPDATE-************************************************************************/

    @Override
    public void update(Product t) throws SQLException {

        Statement st = cnx.createStatement();


        String query = "UPDATE `product` SET `title` = '" + t.getTitle() + "',`description` = '" + t.getDescription()
                + "', `qte` = '" + t.getQte() + "', `price` = '" + t.getPrice() + "', `category_id` = '" + t.getCategory_id() + "', `disable` = '"
                + t.getDisable()  + "' WHERE `product`.`id` = " + t.getId() + " ;";
        st.executeUpdate(query);
    }

    /***************************************************-DELETE-************************************************************************/

    @Override
    public void delete(Long id) throws SQLException {

        Statement st = cnx.createStatement();
        String query = "DELETE FROM `product` WHERE `product`.`id` = '" + id + "'";
        st.executeUpdate(query);
    }


    /***************************  service FILEPRODUCT  ******************************************/

    public List<productFile> readData() throws SQLException {
        List<productFile> ls = new ArrayList<productFile>();
        Statement st = cnx.createStatement();
        String req = "select * from prod_imag_view order by id";
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Integer id = rs.getInt("id");
            String title =rs.getString("title");
            String description=rs.getString("description");
            String name = rs.getString("name");

            productFile p = new productFile(id,title,description,name);
            ls.add(p);
        }

        return ls;
    }
    public List<productFile> readLastData() throws SQLException {
        List<productFile> ls = new ArrayList<productFile>();
        Statement st = cnx.createStatement();
        String req = "select * from prod_imag_view_5 order by id";
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Integer id = rs.getInt("id");
            String title =rs.getString("title");
            String description=rs.getString("description");
            String name = rs.getString("name");

            productFile p = new productFile(id,title,description,name);
            ls.add(p);
        }

        return ls;
    }


}
