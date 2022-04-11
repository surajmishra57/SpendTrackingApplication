package com.incs.SpendTrackingApplication.repository;

import com.incs.SpendTrackingApplication.entity.OrderInfo;
import com.incs.SpendTrackingApplication.request.TopSpend;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInfoRepository extends MongoRepository<OrderInfo,String> {
    @Query("{'email' : ?0 }")
    public List<OrderInfo> findByEmail(String email);


    @Aggregation(pipeline = {"{'$group' : {'_id' : '$email',totalPayment : {$sum : '$totalPayment'}}}"})
    public List<OrderInfo> findMostSpendUser();
}

//db.per.aggregate([{$group : {_id : "$gender","total" : {$sum : "$age"}}},{$sort : {"_id.total" : 1}}])