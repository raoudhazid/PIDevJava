package Service;

import Entity.Voiture;
import helpers.DbConnect;
import IntService.IService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ServiceFormation implements IService<Voiture> {
    Connection cnx;

    public ServiceFormation() {
        cnx = DbConnect.getConnect();
    }

    @Override
    public void add(Voiture t) throws SQLException {

        Statement st = cnx.createStatement();
        String query = "insert into voiture (id,matricule,nbplace,prix,marque,modele,color,image)values(NULL, '" + t.getMatricule() + "', '" + t.getNbplace() + "', '" +t.getPrix()+ "', '" + t.getMarque() +"','"+t.getModele()+ "','" + t.getColor() + "','" + t.getImage() + "')";
        //"INSERT INTO `tactor` (`id`, `name`, `born`, `description`, `image`) VALUES (NULL, '"+t.getName()+"', '"+ t.getBorn() +"', '"+t.getDescription() +"','"+t.getImage()+"');";
        st.executeUpdate(query);

    }

    @Override
    public List<Voiture> read() throws SQLException {
        List<Voiture> ls = new ArrayList<Voiture>();
        Statement st = cnx.createStatement();
        String req = "select * from voiture order by id";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String matricule = rs.getString("matricule");
            //   LocalDate born = rs.getDate("born").toLocalDate();
            String nbplace = rs.getString("description");
            String prix = rs.getString("prix");
            String marque = rs.getString("marque");
            String modele = rs.getString("modele");
            String color = rs.getString("color");
            String image = rs.getString("image");
            Voiture p = new Voiture(id,matricule,nbplace,prix,marque,modele,color,image);
            ls.add(p);
        }

        return ls;
    }


    @Override
    public void update(Voiture t) throws SQLException {

        Statement st = cnx.createStatement();


        String query = "UPDATE `Voiture` SET `matricule` = '" + t.getMatricule() + "',`nbplace` = '" + t.getNbplace() + "', `prix` = '" + t.getPrix() + "', `marque` = '" + t.getMarque() + "', `modele` = '" + t.getModele() + "', `color` = '"
                + t.getColor() + "', `image` = '" + t.getImage() + "' WHERE `Voiture`.`id` = " + t.getId() + " ''";
        st.executeUpdate(query);
    }

    @Override
    public void delete(Long id) throws SQLException {

        Statement st = cnx.createStatement();
        String query = "DELETE FROM `Voiture` WHERE `Voiture`.`id` = '" + id + "'";
        st.executeUpdate(query);

    }




}

