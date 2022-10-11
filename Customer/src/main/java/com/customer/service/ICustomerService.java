package com.customer.service;

import com.customer.exception.CustomerNotFoundException;
import com.customer.model.AuthenticationRequest;
import com.customer.model.Customer;
import com.customer.model.CustomerDTO;
import com.customer.model.CustomerLogin;

import java.util.List;

public interface ICustomerService {

    public CustomerLogin registerCustomer(AuthenticationRequest authenticationRequest);
    public Customer addCustomerDetails(CustomerDTO customerDTO,String username);
    public List<Customer> findCustomerDetails(String username) throws CustomerNotFoundException;
}
