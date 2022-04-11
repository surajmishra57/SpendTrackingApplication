package com.incs.SpendTrackingApplication.repository;

import com.incs.SpendTrackingApplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    public Product findByProductId(String id);

    @Query(value = " select * from product left join category on product.category_id = category.category_id where category.category_type=:name limit 5",nativeQuery = true)
    public List<Product> findByCategoryName(String name);
}
