package com.incs.SpendTrackingApplication.service;

import com.incs.SpendTrackingApplication.entity.*;
import com.incs.SpendTrackingApplication.exception.ValidationException;
import com.incs.SpendTrackingApplication.repository.ActivityRepository;
import com.incs.SpendTrackingApplication.repository.OrderInfoRepository;
import com.incs.SpendTrackingApplication.repository.ProductRepository;
import com.incs.SpendTrackingApplication.repository.UserRepository;
import com.incs.SpendTrackingApplication.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderInfoRepository orderInfoRepository;
    @Autowired
    private ActivityRepository activityRepository;

    public UserDto registerUser(UserDto userDto) throws ValidationException {
        if (repository.findByUserEmail(userDto.getEmail()) != null)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User With This Name Already Exist");
        double balance;
        User admin = repository.findByUserEmail("admin@gamil.com");
        if(admin==null)
            balance=2000.00;
        balance = admin.getUserWallet().getWalletAmount();

        User userData = new User();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userData.setUserId(UUID.randomUUID().toString());
        userData.setUserFirstName(userDto.getFirstName());
        userData.setUserLastName(userDto.getLastName());
        userData.setUserEmail(userDto.getEmail());
        userData.setUserPhone(userDto.getPhone());
        userData.setUserPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setPassword(userData.getUserPassword());
        userData.setUserCreatedAt(new Date());
        userData.setUserUpdatedAt(new Date());
        userData.setUserRole(new Role(
                UUID.randomUUID().toString(), "USER"
        ));
        userData.setUserWallet(new Wallet(UUID.randomUUID().toString(), balance, null));
        repository.save(userData);
        return userDto;
    }

    public UserDto updateUser(UserDto userDto) {
        String email =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByUserEmail(email);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserFirstName(userDto.getFirstName());
        user.setUserLastName(userDto.getLastName());
        user.setUserEmail(userDto.getEmail());
        user.setUserUpdatedAt(new Date());
        user.setUserPhone(userDto.getPhone());
        user.setUserPassword(userDto.getPassword());
        user.setUserPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setPassword(user.getUserPassword());
        return userDto;
    }

    public String deleteUser() {
        String email =  SecurityContextHolder.getContext().getAuthentication().getName();;
        User user = repository.findByUserEmail(email);
        repository.deleteById(user.getUserId());
        return "Account Deleted";
    }

    public Wallet getWallet() {
        String email =  SecurityContextHolder.getContext().getAuthentication().getName();;
        User user = repository.findByUserEmail(email);
        return user.getUserWallet();
    }

    public List<ProductDto> findProductByCategory(String categoryName) throws ValidationException {
        List<Product> pro = (List<Product>) productRepository.findByCategoryName(categoryName);
        if (pro.size() < 0)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Category With This Name Does Not Exist");
        else {
            List<ProductDto> productDtoList = new ArrayList<>();
            for (Product p : pro) {
                ProductDto productDto = new ProductDto();
                productDto.setId(p.getProductId());
                productDto.setName(p.getProductName());
                productDto.setPrice(p.getProductPrice());
                productDto.setProductDescription(p.getProductDescription());
                productDto.setQuantity(p.getQuantity());
                productDto.setCategoryDetail(p.getProductCategory().getCategoryType());
                productDtoList.add(productDto);
            }
            return productDtoList;
        }
    }

    public ReceiptDto buyProduct(QuantityDto quantityDto, String id) throws ValidationException {

        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User userInformation = repository.findByUserEmail(email);

        Product productInformation = productRepository.findByProductId(id);
       if(productInformation==null)
           throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product ID Is Not Valid !!!");

       if(productInformation.getQuantity()<quantityDto.getProductQuantity())
           throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Insufficient Quantity  !!!");
       double total = productInformation.getProductPrice()*quantityDto.getProductQuantity();
       if(total > userInformation.getUserWallet().getWalletAmount())
           throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Insufficient Amount !!!");
       double walletAmount = userInformation.getUserWallet().getWalletAmount();
       userInformation.getUserWallet().setWalletAmount(walletAmount-total);
       int quantity = productInformation.getQuantity();
       productInformation.setQuantity(quantity-quantityDto.getProductQuantity());

       productRepository.save(productInformation);
        System.out.println("Product Save");
      // userInformation.setUserProducts(Arrays.asList(productInformation));
       repository.save(userInformation);
        System.out.println("User Save");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userInformation.getUserId());
        orderInfo.setUserFirstName(userInformation.getUserFirstName());
        orderInfo.setUserLastName(userInformation.getUserLastName());
        orderInfo.setEmail(userInformation.getUserEmail());
        orderInfo.setPhone(userInformation.getUserPhone());
        orderInfo.setProductId(productInformation.getProductId());
        orderInfo.setProductName(productInformation.getProductName());
        orderInfo.setProductDescription(productInformation.getProductDescription());
        orderInfo.setProductPrice(productInformation.getProductPrice());
        orderInfo.setProductQuantity(quantityDto.getProductQuantity());
        orderInfo.setTotalPayment(total);
        orderInfo.setProductCategory(productInformation.getProductCategory().getCategoryType());
        orderInfo.setProductBroughtAt(new Date());
        orderInfoRepository.save(orderInfo);
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setProductId(productInformation.getProductId());
        receiptDto.setProductName(productInformation.getProductName());
        receiptDto.setProductQuantity(quantityDto.getProductQuantity());
        receiptDto.setProductPrice(productInformation.getProductPrice());
        receiptDto.setDebitedAmount(total);
        receiptDto.setAvailableWalletAmount(userInformation.getUserWallet().getWalletAmount());
        receiptDto.setTransactionDate(new Date());
         return receiptDto;
    }

    public List<OrderInfo> getOrderedProductInfo() {
        String email =  SecurityContextHolder.getContext().getAuthentication().getName();
        return (List<OrderInfo>) orderInfoRepository.findByEmail(email);
    }

    public List<User> getAllUser() {
        return (List<User>) repository.findAll();
    }

    public User findUser(String email) throws ValidationException {
        User user = repository.findByUserEmail(email);
        if(user==null)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Not Found !!!");
        return user;
    }

    public String blockUser(String email) throws ValidationException {
        User user = repository.findByUserEmail(email);
        if(user==null)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Not Found !!!");
        user.setUserBlocked(true);
        repository.save(user);
        return "User Is Blocked Successfully";
    }

    public String updateWallet(String email,Wallet wallet) throws ValidationException {
        User user = repository.findByUserEmail(email);
        if(user==null)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Not Found !!!");
        user.getUserWallet().setWalletAmount(wallet.getWalletAmount());
        repository.save(user);
        return "User Wallet Updated Successfully";
    }

    public List<Activity> viewActivity(String email) throws ValidationException {
        List<Activity> activityList = (List<Activity>)activityRepository.findByEmail(email);
        if(activityList.size()<0)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Not Found !!!");
        return activityList;

    }

    public List<TopSpend> topSpendUser() throws ValidationException {
        List<OrderInfo> orderInfos = (List<OrderInfo>) orderInfoRepository.findMostSpendUser();
        if(orderInfos.size()<0)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Something  Went Wrong :( !!!");
        List<TopSpend> topSpends = new ArrayList<>();
        for(OrderInfo o : orderInfos)
        {
            TopSpend topSpend = new TopSpend();
            topSpend.setUserEmail(o.getId());
            topSpend.setTotalSpend(o.getTotalPayment());
            topSpends.add(topSpend);
        }
        return topSpends;

    }

    public List<OrderInfo> getAllOrderHistory(String email) throws ValidationException {
        List<OrderInfo> orderInfos = (List<OrderInfo>) orderInfoRepository.findByEmail(email);
        if(orderInfos.size()<0)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Something  Went Wrong :( !!!");
        return orderInfos;

    }
}
