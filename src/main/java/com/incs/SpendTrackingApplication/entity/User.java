package com.incs.SpendTrackingApplication.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @Column(name = "user_id",unique = true)
    private String userId;
    private String userFirstName;
    private String userLastName;
    @Column(name = "user_email",unique = true)
    private String userEmail;
    private String userPhone;
    private String userPassword;
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCreatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date userUpdatedAt;
    private Boolean isUserBlocked = false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="walletId",referencedColumnName = "walletId")
    private Wallet userWallet;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="roleId",referencedColumnName = "roleId")
    private Role userRole;
    @ManyToMany
    @JoinTable(name="user_product_tb",
            joinColumns = @JoinColumn(name="user_id",referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name="product_id",referencedColumnName = "productId"))
    private List<Product> userProducts;

    public User(String userId, String userFirstName, String userLastName, String userEmail, String userPhone, String userPassword, Date userCreatedAt, Date userUpdatedAt, Boolean isUserBlocked, Wallet userWallet, Role userRole, List<Product> userProducts) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userCreatedAt = userCreatedAt;
        this.userUpdatedAt = userUpdatedAt;
        this.isUserBlocked = isUserBlocked;
        this.userWallet = userWallet;
        this.userRole = userRole;
        this.userProducts = userProducts;
    }

    public User()
    {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getUserCreatedAt() {
        return userCreatedAt;
    }

    public void setUserCreatedAt(Date userCreatedAt) {
        this.userCreatedAt = userCreatedAt;
    }

    public Date getUserUpdatedAt() {
        return userUpdatedAt;
    }

    public void setUserUpdatedAt(Date userUpdatedAt) {
        this.userUpdatedAt = userUpdatedAt;
    }

    public Boolean getUserBlocked() {
        return isUserBlocked;
    }

    public void setUserBlocked(Boolean userBlocked) {
        isUserBlocked = userBlocked;
    }

    public Wallet getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(Wallet userWallet) {
        this.userWallet = userWallet;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public List<Product> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(List<Product> userProducts) {
        this.userProducts = userProducts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCreatedAt=" + userCreatedAt +
                ", userUpdatedAt=" + userUpdatedAt +
                ", isUserBlocked=" + isUserBlocked +
                ", userWallet=" + userWallet +
                ", userRole=" + userRole +
                ", userProducts=" + userProducts +
                '}';
    }
}
