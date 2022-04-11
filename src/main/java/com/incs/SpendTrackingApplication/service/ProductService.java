package com.incs.SpendTrackingApplication.service;

import com.incs.SpendTrackingApplication.entity.Category;
import com.incs.SpendTrackingApplication.entity.Product;
import com.incs.SpendTrackingApplication.exception.ValidationException;
import com.incs.SpendTrackingApplication.repository.ProductRepository;
import com.incs.SpendTrackingApplication.request.ProductDto;
import com.incs.SpendTrackingApplication.request.QuantityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductDto addProduct(ProductDto productDto) {
        Product productDetails = new Product();
        productDetails.setProductId(UUID.randomUUID().toString());
        productDto.setId(productDetails.getProductId());
        productDetails.setProductName(productDto.getName());
        productDetails.setProductDescription(productDto.getProductDescription());
        productDetails.setQuantity(productDto.getQuantity());
        productDetails.setProductPrice(productDto.getPrice());
        productDetails.setProductCreatedAt(new Date());
        productDetails.setProductUpdatedAt(new Date());
        productDetails.setProductCategory(new Category(UUID.randomUUID().toString(),productDto.getCategoryDetail(),null));
        if(productRepository.save(productDetails)==null)
            return null;
        return productDto;
    }

    public ProductDto updateProduct(ProductDto productDto,String id) {
        Product productInfo = productRepository.findByProductId(id);
        if(productInfo==null)
            return null;
        productInfo.setProductUpdatedAt(new Date());
        productInfo.setProductName(productDto.getName());
        productInfo.setProductPrice(productDto.getPrice());
        productInfo.setProductDescription(productDto.getProductDescription());
        productInfo.setQuantity(productDto.getQuantity());
        Category cat = productInfo.getProductCategory();
        cat.setCategoryType(productDto.getCategoryDetail());
        productInfo.setProductCategory(cat);
        productDto.setId(productInfo.getProductId());
        if(productRepository.save(productInfo)==null)
            return null;
        return productDto;
    }

    public List<Product> getAllProduct() {
        return (List<Product>)productRepository.findAll();
    }

    public Product getProduct(String id) throws ValidationException {
        Product product = productRepository.findByProductId(id);
        if(product==null)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(),"Product ID Is Not Valid !!!");
        return product;
    }

    public String deleteProduct(String id) throws ValidationException {
        Product product = productRepository.findByProductId(id);
        if(product==null)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(),"Product ID Is Not Valid !!!");
        productRepository.deleteById(product.getProductId());
        return "Product Has Been Deleted " + product.getProductId();
    }

    public String updateProductQuantity(QuantityDto quantityDto, String id) throws ValidationException {
        Product product = productRepository.findByProductId(id);
        if(product==null)
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(),"Product ID Is Not Valid !!!");
        product.setQuantity(quantityDto.getProductQuantity());
        productRepository.save(product);
        return "Product "+  product.getProductDescription() +" Quantity Has Been Updated By" + quantityDto.getProductQuantity();
    }
}
