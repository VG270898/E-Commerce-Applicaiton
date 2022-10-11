package com.customer.repository;

import com.customer.model.CustomerLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerLoginRepo extends JpaRepository<CustomerLogin,Long> {
    CustomerLogin findByUsername(String username);
}
