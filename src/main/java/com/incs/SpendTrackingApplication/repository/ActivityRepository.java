package com.incs.SpendTrackingApplication.repository;

import com.incs.SpendTrackingApplication.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity,String> {
    @Query("{'email' : ?0 }")
    public List<Activity> findByEmail(String email);
}
