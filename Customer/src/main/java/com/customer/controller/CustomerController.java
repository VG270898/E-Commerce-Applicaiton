package com.customer.controller;

import com.customer.exception.CustomerNotFoundException;
import com.customer.model.AuthenticationRequest;
import com.customer.model.AuthenticationResponse;
import com.customer.model.CustomerDTO;
import com.customer.service.CustomUserDetailService;
import com.customer.service.CustomerServiceImpl;
import com.customer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    AuthenticationResponse authenticationResponse= new AuthenticationResponse();
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("In customer authentication");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage());
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = customUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        System.out.println(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println(jwt);
        authenticationResponse.setJwt(jwt);
        return ResponseEntity.ok(authenticationResponse.getJwt());
    }

    @PostMapping("/register")
    public ResponseEntity registerCustomers(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(customerServiceImpl.registerCustomer(authenticationRequest));
    }

    @GetMapping("/getcustomer")
    public ResponseEntity findCustomerByUsername() throws CustomerNotFoundException {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)auth).getUsername();
        return ResponseEntity.ok(customerServiceImpl.findCustomerDetails(username));
    }

    @PostMapping("/addDetails")
    public ResponseEntity addCustomerDetails(@RequestBody @Valid CustomerDTO customerDTO){
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)auth).getUsername();
        return ResponseEntity.ok(customerServiceImpl.addCustomerDetails(customerDTO,username));
    }

}
