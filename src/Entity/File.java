package Entity;

public class File {
    int id;
    int product_id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", name='" + name + '\'' +
                '}';
    }

    public File(int id, int product_id, String name) {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
    }
}
