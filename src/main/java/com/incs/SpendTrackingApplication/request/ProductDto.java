package com.incs.SpendTrackingApplication.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductDto {
    private String id;
    @NotEmpty(message = "Product Name Required")
    private String name;
    @NotEmpty(message = "Product Description Required")
    private String productDescription;
    @NotNull(message = "Product Quantity Required")
    @Positive(message = "Quantity Must Positive Value")
    private int quantity;
    @NotNull(message = "Product Price Required")
    @Positive(message = "Product Price Must Be Positive")
    private double price;
    @NotEmpty(message = "Product Category Required")
    private String categoryDetail;

    public ProductDto() {
    }

    public ProductDto(String id, String name, String productDescription, int quantity, double price, String categoryDetail) {
        this.id = id;
        this.name = name;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
        this.categoryDetail = categoryDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryDetail() {
        return categoryDetail;
    }

    public void setCategoryDetail(String categoryDetail) {
        this.categoryDetail = categoryDetail;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", categoryDetail='" + categoryDetail + '\'' +
                '}';
    }
}