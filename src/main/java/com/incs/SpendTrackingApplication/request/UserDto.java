package com.incs.SpendTrackingApplication.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class UserDto {
    @NotEmpty(message = "First Name Required")
    private String firstName;
    @NotEmpty(message = "Last Name Required")
    private String lastName;
    @NotEmpty(message = "Email Required")
    @Email(message = "Invalid Email")
    private String email;
    @NotEmpty(message = "Phone Number Required")
    @Size(min=10,max=10,message = "Phone Number must have 10 digits")
    @PositiveOrZero(message = "Provide Valid Phone Number")
    private String phone;
    @NotEmpty(message = "Password Required")
    @Size(min=8,message = "8 Characters Required")
    private String password;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
