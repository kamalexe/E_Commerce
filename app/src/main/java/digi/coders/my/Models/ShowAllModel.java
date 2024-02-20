package digi.coders.my.Models;

import java.io.Serializable;

public class ShowAllModel implements Serializable {

    String description;
    String name;
    String rating;
    int price;
    String img_uri;
    String type;

    public ShowAllModel() {
    }

    public ShowAllModel(String description, String name, String rating, int price, String img_uri, String type) {
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.img_uri = img_uri;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}



