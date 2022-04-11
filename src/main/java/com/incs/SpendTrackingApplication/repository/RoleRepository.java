package com.incs.SpendTrackingApplication.repository;

import com.incs.SpendTrackingApplication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
}
