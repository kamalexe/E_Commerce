package digi.coders.my.Models;

public class CategoryModel {
    String img_uri;
    String name;
    String type;

    public CategoryModel() {
    }
    public CategoryModel(String img_uri, String name, String type) {
        this.img_uri = img_uri;
        this.name = name;
        this.type = type;
    }

    public String getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(String img_uri) {
        this.img_uri = img_uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
