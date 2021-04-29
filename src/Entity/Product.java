package Entity;

public class Product {
    Integer id;
    String title;
    String description;
    String qte;
    String price ;
    String category_id;
    String disable;

  /***************************************************-CONTRUCTEUR-************************************************************************/
    public Product(Integer id, String title, String description, String qte, String price, String category_id, String disable){

        this.id=id;
        this.title=title;
        this.description=description;
        this.qte=qte;
        this.price=price;
        this.category_id=category_id;
        this.disable=disable;

    }
    /***************************************************-CONTRUCTEUR-************************************************************************/

    /***************************************************-toString-************************************************************************/
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", qte='" + qte + '\'' +
                ", price='" + price + '\'' +
                ", category_id='" + category_id + '\'' +
                ", disable='" + disable + '\'' +
                '}';
    }
    /***************************************************-toString-************************************************************************/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }
}
