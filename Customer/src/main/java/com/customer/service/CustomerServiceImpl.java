package com.customer.service;

import com.customer.exception.CustomerNotFoundException;
import com.customer.model.AuthenticationRequest;
import com.customer.model.Customer;
import com.customer.model.CustomerDTO;
import com.customer.model.CustomerLogin;
import com.customer.repository.CustomerLoginRepo;
import com.customer.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerLoginRepo customerLoginRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerLogin registerCustomer(AuthenticationRequest authenticationRequest) {
//        authenticationRequest.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
        CustomerLogin customerLogin = new CustomerLogin();
        customerLogin.setUsername(authenticationRequest.getUsername());
        customerLogin.setPassword(authenticationRequest.getPassword());
        return customerLoginRepo.save(customerLogin);

    }

    @Override
    public Customer addCustomerDetails(CustomerDTO customerDTO,String username) {
        Customer customer = new Customer();
        customer.setCustomerId(customerLoginRepo.findByUsername(username).getCustomerId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerGender(customerDTO.getCustomerGender());
        customer.setCustomerAge(customerDTO.getCustomerAge());
        customer.setCustomerAddress(customerDTO.getCustomerAddress());

        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> findCustomerDetails(String username) throws CustomerNotFoundException {
        Long customerId = customerLoginRepo.findByUsername(username).getCustomerId();
        System.out.println(customerId);
        if (customerRepo.findById(customerId).isEmpty()) {
            throw new CustomerNotFoundException("Customer Not Found with username " + username);
        } else {
            return customerRepo.findById(customerId).stream().toList();
        }
    }
}
