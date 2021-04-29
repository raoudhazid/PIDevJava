package Entity;

public class productFile {
    private int id;
    private String title;
    private String description;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "productFile{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(int id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public productFile(int id, String title, String description, String name) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.name = name;
    }

    private String name;

}