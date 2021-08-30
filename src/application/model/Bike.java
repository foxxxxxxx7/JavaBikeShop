package application.model;

public class Bike {
    public int bikeID;
    private String size;
    private String style;
    private String gender;
    private double price;
    private String condition;
    private String comment;
    private boolean inStock;

    public Bike(int bikeID, String size, String style, String gender, double price, String condition, String comment, boolean inStock) {
        this.bikeID = bikeID;
        this.size = size;
        this.style = style;
        this.gender = gender;
        this.price = price;
        this.condition = condition;
        this.comment = comment;
        this.inStock = inStock;
    }

    public Bike(int bikeID, String size, String style, String gender, double price, String condition, String comment) {
        this.bikeID = bikeID;
        this.size = size;
        this.style = style;
        this.gender = gender;
        this.price = price;
        this.condition = condition;
        this.comment = comment;
        this.inStock = true;
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStringPrice() {
        String stringPrice = String.valueOf(this.price);
        return stringPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "bikeID=" + bikeID +
                ", size='" + size + '\'' +
                ", style='" + style + '\'' +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                ", condition='" + condition + '\'' +
                ", comment='" + comment + '\'' +
                ", inStock=" + inStock +
                '}';
    }
}
