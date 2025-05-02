package uz.azamat.product;

import java.util.Objects;

public class Product {
    private String photoId;
    private String name;
    private Integer price;
    private Integer quantity;
    private boolean isSold = false;

    public Product() {
    }

    public Product(String photoId, String name, Integer price, Integer quantity, boolean isSold) {
        this.photoId = photoId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isSold = isSold;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return isSold() == product.isSold() && Objects.equals(getPhotoId(), product.getPhotoId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getPrice(), product.getPrice()) && Objects.equals(getQuantity(), product.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhotoId(), getName(), getPrice(), getQuantity(), isSold());
    }

    @Override
    public String toString() {
        return "Product{" +
               "photoId='" + photoId + '\'' +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", quantity=" + quantity +
               ", isSold=" + isSold +
               '}';
    }
}
