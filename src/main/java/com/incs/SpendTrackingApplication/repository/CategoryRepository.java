package com.incs.SpendTrackingApplication.repository;

import com.incs.SpendTrackingApplication.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {

}
