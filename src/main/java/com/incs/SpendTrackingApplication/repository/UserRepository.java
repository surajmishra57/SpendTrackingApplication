package com.incs.SpendTrackingApplication.repository;

import com.incs.SpendTrackingApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public User findByUserEmail(String email);


}
