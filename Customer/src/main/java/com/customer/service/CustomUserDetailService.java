package com.customer.service;

import com.customer.model.CustomerLogin;
import com.customer.repository.CustomerLoginRepo;
import com.customer.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerLoginRepo customerLoginRepo;
    @Autowired
    private CustomerRepo customerRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomerLogin customerLogin = customerLoginRepo.findByUsername(username);

        if(customerLogin ==null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new PrincipalCustomer(customerLogin);

    }

}



