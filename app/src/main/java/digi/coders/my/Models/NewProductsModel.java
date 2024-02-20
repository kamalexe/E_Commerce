package digi.coders.my.Models;

import java.io.Serializable;

public class NewProductsModel implements Serializable  {
    String description;
    String name;
    String rating;
    int price;
    String img_uri;

    public NewProductsModel() {
    }

    public NewProductsModel(String description, String name, String rating, int price, String img_uri) {
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.img_uri = img_uri;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(String img_uri) {
        this.img_uri = img_uri;
    }
}
