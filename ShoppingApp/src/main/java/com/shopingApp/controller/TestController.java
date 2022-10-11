package com.shopingApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopingApp.model.ResponseToken;
import com.shopingApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;


    ResponseToken responseToken = new ResponseToken();
    String jwt;
    private static final String REGISTRATION_URL = "http://localhost:9002/customer/register";
    private static final String AUTHENTICATION_URL = "http://localhost:9002/customer/authenticate";
    private static final String HELLO_URL = "http://localhost:8080/helloadmin";


    @PostMapping("/customer-register")
    public ResponseEntity customerRegistration(@RequestBody User user) throws JsonProcessingException {
        String response = null;
        String registrationBody = getBody(user);
        HttpHeaders registrationHeaders = getHeaders();
        HttpEntity<String> registrationEntity = new HttpEntity<String>(registrationBody, registrationHeaders);
        try{
            ResponseEntity<String> registrationResponse = restTemplate.exchange(REGISTRATION_URL, HttpMethod.POST,registrationEntity, String.class);

            if (registrationResponse.getStatusCode().equals(HttpStatus.OK)) {
                response="Customer Registered Successfully!";
            }
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }


    @RequestMapping(value="/customer-login",method = RequestMethod.POST)
    public String authenticateCustomer(@RequestBody User user) throws JsonProcessingException {

        String authenticationBody = getBody(user);
        // create headers specifying that it is JSON request
        HttpHeaders authenticationHeaders = getHeaders();
        HttpEntity<String> authenticationEntity = new HttpEntity<String>(authenticationBody, authenticationHeaders);

        // Authenticate User and get JWT
        ResponseEntity<String> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,HttpMethod.POST,authenticationEntity,String.class);
        responseToken.setToken(authenticationResponse.getBody());
        // if the authentication is successful
        try{
            if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
                jwt=authenticationResponse.getBody();
            }
        }catch (Exception e){
            return e.getMessage();
        }
        return jwt;
    }

    @GetMapping("/getDetails")
    public String getDetails(){
        String response = null;
        String token = "Bearer " + responseToken.getToken();
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
        // Use Token to get Response
        ResponseEntity<String> helloResponse = restTemplate.exchange(HELLO_URL, HttpMethod.GET, jwtEntity,
                String.class);
        if (helloResponse.getStatusCode().equals(HttpStatus.OK)) {
            response = helloResponse.getBody();
        }
        return response;
    }


//    @RequestMapping(value = "/getResponse", method = RequestMethod.GET)
//    public String getResponse() throws JsonProcessingException {
//
//        String response = null;
//        // create user registration object
//        RegistrationUser registrationUser = getRegistrationUser();
//        // convert the user registration object to JSON
//        String registrationBody = getBody(registrationUser);
//        // create headers specifying that it is JSON request
//        HttpHeaders registrationHeaders = getHeaders();
//        HttpEntity<String> registrationEntity = new HttpEntity<String>(registrationBody, registrationHeaders);
//
//        try {
//            // Register User
//            ResponseEntity<String> registrationResponse = restTemplate.exchange(REGISTRATION_URL, HttpMethod.POST,
//                    registrationEntity, String.class);
//            // if the registration is successful
//            if (registrationResponse.getStatusCode().equals(HttpStatus.OK)) {
//
//                // create user authentication object
//                User authenticationUser = getAuthenticationUser();
//                // convert the user authentication object to JSON
//                String authenticationBody = getBody(authenticationUser);
//                // create headers specifying that it is JSON request
//                HttpHeaders authenticationHeaders = getHeaders();
//                HttpEntity<String> authenticationEntity = new HttpEntity<String>(authenticationBody,
//                        authenticationHeaders);
//
//                // Authenticate User and get JWT
//                ResponseEntity<ResponseToken> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
//                        HttpMethod.POST, authenticationEntity, ResponseToken.class);
//
//                // if the authentication is successful
//                if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
//                    String token = "Bearer " + authenticationResponse.getBody().getToken();
//                    HttpHeaders headers = getHeaders();
//                    headers.set("Authorization", token);
//                    HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
//                    // Use Token to get Response
//                    ResponseEntity<String> helloResponse = restTemplate.exchange(HELLO_URL, HttpMethod.GET, jwtEntity,
//                            String.class);
//                    if (helloResponse.getStatusCode().equals(HttpStatus.OK)) {
//                        response = helloResponse.getBody();
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        return response;
//    }

//    private RegistrationUser getRegistrationUser() {
//        RegistrationUser user = new RegistrationUser();
//        user.setUsername("javainuse");
//        user.setPassword("javainuse");
//        user.setRole("ROLE_USER");
//        return user;
//    }
//
//    private User getAuthenticationUser() {
//        User user = new User();
//        user.setUsername("javainuse");
//        user.setPassword("javainuse");
//        return user;
//    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private String getBody(final User user) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(user);
    }
}
