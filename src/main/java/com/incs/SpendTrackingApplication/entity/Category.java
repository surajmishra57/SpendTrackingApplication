package com.incs.SpendTrackingApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Category {
    @Id
    private String categoryId;
    private String categoryType;
    @OneToMany(mappedBy = "productCategory")
    @JsonIgnore
    private List<Product> products;

    public Category() {
    }

    public Category(String categoryId, String categoryType, List<Product> products) {
        this.categoryId = categoryId;
        this.categoryType = categoryType;
        this.products = products;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryType='" + categoryType + '\'' +
                ", products=" + products +
                '}';
    }
}
