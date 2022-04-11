package com.incs.SpendTrackingApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="categoryId",referencedColumnName = "categoryId")
    private Category productCategory;

    private Date productCreatedAt;
    private Date productUpdatedAt;

    @ManyToMany(mappedBy = "userProducts")
    @JsonIgnore
    private List<User> productUser;

    public Product() {
    }

    public Product(String productId, String productName, String productDescription, Double productPrice, int quantity, Category productCategory, Date productCreatedAt, Date productUpdatedAt, List<User> productUser) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.productCategory = productCategory;
        this.productCreatedAt = productCreatedAt;
        this.productUpdatedAt = productUpdatedAt;
        this.productUser = productUser;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public Date getProductCreatedAt() {
        return productCreatedAt;
    }

    public void setProductCreatedAt(Date productCreatedAt) {
        this.productCreatedAt = productCreatedAt;
    }

    public Date getProductUpdatedAt() {
        return productUpdatedAt;
    }

    public void setProductUpdatedAt(Date productUpdatedAt) {
        this.productUpdatedAt = productUpdatedAt;
    }

    public List<User> getProductUser() {
        return productUser;
    }

    public void setProductUser(List<User> productUser) {
        this.productUser = productUser;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", productCategory=" + productCategory +
                ", productCreatedAt=" + productCreatedAt +
                ", productUpdatedAt=" + productUpdatedAt +
                ", productUser=" + productUser +
                '}';
    }
}
