package com.incs.SpendTrackingApplication.controller;

import com.incs.SpendTrackingApplication.entity.*;
import com.incs.SpendTrackingApplication.exception.ValidationException;
import com.incs.SpendTrackingApplication.request.ProductDto;
import com.incs.SpendTrackingApplication.request.QuantityDto;
import com.incs.SpendTrackingApplication.request.TopSpend;
import com.incs.SpendTrackingApplication.service.ProductService;
import com.incs.SpendTrackingApplication.service.UserService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    //=============================User Related Admin Role ===============================

    //Get All User
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("user/users")
    public List<User> getAllUser()
    {
      return userService.getAllUser();
    }

    //Get User By Id
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/{email}")
    public User getUser(@PathVariable("email") String email) throws ValidationException {
        return userService.findUser(email);
    }

    //Update User Wallet
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("user/updateWallet/{email}")
    public String updateWallet(@Valid @RequestBody Wallet wallet, @PathVariable("email") String email) throws ValidationException {
        return userService.updateWallet(email,wallet);
    }

    //Block User
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("user/blockUser/{email}")
    public String blockUser(@PathVariable("email") String email) throws ValidationException {
        return userService.blockUser(email);
    }

    //View Activity
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("user/viewActivity/{email}")
    public List<Activity> viewActivity(@PathVariable("email") String email) throws ValidationException {
        return userService.viewActivity(email);
    }


    //View Top Spend User
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("user/viewTopSpendUser/")
    public List<TopSpend> viewTopSpendUser() throws ValidationException {
        return userService.topSpendUser();
    }

    //User Order History
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("user/userOrderHistory/{email}")
    public List<OrderInfo> getAllOrderHistory(@PathVariable("email") String email) throws ValidationException {
        return userService.getAllOrderHistory(email);
    }
    //=============================Product Related Admin Role =============================

    //Add Product
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/product/add")
    public ProductDto addProduct(@Valid @RequestBody ProductDto productDto)
    {
        return productService.addProduct(productDto);
    }


    //Update Product
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product/update/{id}")
    public ProductDto updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable("id") String id)
    {
        return productService.updateProduct(productDto,id);
    }


    //Delete Product
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) throws ValidationException {
        return productService.deleteProduct(id);
    }


    //Update Quantity
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product/quantity/{id}")
    public String updateQuantity(@Valid @RequestBody QuantityDto quantityDto, @PathVariable("id") String id) throws ValidationException {
        return productService.updateProductQuantity(quantityDto,id);
    }


    // Get All Product
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/product/all")
    public List<Product> getAll()
    {
        return productService.getAllProduct();
    }


    // Get Product By ID
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") String id) throws ValidationException {
        return productService.getProduct(id);
    }
}
