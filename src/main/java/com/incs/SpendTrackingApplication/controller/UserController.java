package com.incs.SpendTrackingApplication.controller;

import com.incs.SpendTrackingApplication.entity.Activity;
import com.incs.SpendTrackingApplication.entity.OrderInfo;
import com.incs.SpendTrackingApplication.entity.User;
import com.incs.SpendTrackingApplication.entity.Wallet;
import com.incs.SpendTrackingApplication.exception.ValidationException;
import com.incs.SpendTrackingApplication.repository.ActivityRepository;
import com.incs.SpendTrackingApplication.repository.UserRepository;
import com.incs.SpendTrackingApplication.request.*;
import com.incs.SpendTrackingApplication.service.UserService;
import com.incs.SpendTrackingApplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;

    //RegisterUser
    @PostMapping("/register")
    public UserDto registerUser(@Valid @RequestBody UserDto userDto) throws ValidationException {
        return service.registerUser(userDto);
    }

    //Login
    @PostMapping("/login")
    String authenticate(@Valid @RequestBody AuthUser authUser) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authUser.getEmail(), authUser.getPassword())
            );
        }
        catch (Exception ex)
        {
            throw new Exception("Invalid username/password");
        }

        User userData = userRepository.findByUserEmail(authUser.getEmail());
        if(userData.getUserBlocked()==true)
            return "You Are Blocked By System Admin ";
        Activity activity = new Activity();
        activity.setActivity("Login");
        activity.setEmail(authUser.getEmail());
        activity.setDate(new Date());
        activityRepository.save(activity);
        return jwtUtil.generateToken(authUser.getEmail(),userData);
    }

    //Update
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/update")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto){
        return service.updateUser(userDto);
    }

    //View Wallet
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/wallet")
    public Wallet getWallet()
    {
        return service.getWallet();
    }
    //View Products
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/viewProducts/{categoryName}")
    public List<ProductDto> findProductByCategory(@PathVariable("categoryName") String categoryName) throws ValidationException {
        return service.findProductByCategory(categoryName);
    }
    //Buy Product
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/buyProduct/{id}")
    public ReceiptDto buyProduct(@RequestBody QuantityDto quantityDto , @PathVariable("id") String id) throws ValidationException {
        return service.buyProduct(quantityDto,id);
    }
    //View Order
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/orders")
    public List<OrderInfo> orderedProducts()
    {
       return service.getOrderedProductInfo();
    }


    //Delete Account
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/delete")
    public String deleteUser()
    {
        return service.deleteUser();
    }



    //Logout
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/logout")
    public String logout()
    {
       Activity activity = new Activity();
       activity.setActivity("Logout");
       activity.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
       activity.setDate(new Date());
       activityRepository.save(activity);
       return "Logout Successfully ";
    }


}
