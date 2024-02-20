package digi.coders.my.Models;

public class MyCartModel {

    String CurrentDate;
    String CurrentTime;
    String ProductName;
    String productPrice;
    String totalQuantity;
    int totalprice;

    public MyCartModel() {
    }

    public MyCartModel(String currentDate, String currentTime, String productName, String productPrice, String totalQuantity, int totalprice) {
        CurrentDate = currentDate;
        CurrentTime = currentTime;
        ProductName = productName;
        this.productPrice = productPrice;
        this.totalQuantity = totalQuantity;
        this.totalprice = totalprice;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }

    public String getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(String currentTime) {
        CurrentTime = currentTime;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
