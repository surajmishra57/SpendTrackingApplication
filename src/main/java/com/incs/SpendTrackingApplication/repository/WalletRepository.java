package com.incs.SpendTrackingApplication.repository;

import com.incs.SpendTrackingApplication.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,String> {
}
