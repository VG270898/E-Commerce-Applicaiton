package com.mobile.controller;

import com.mobile.model.Mobile;
import com.mobile.service.MobileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/mobile")
public class MobileController {
    private static final Logger LOG = Logger.getLogger(MobileController.class.getName());
    @Autowired
    private MobileServiceImpl mobileServiceImpl;

    String response;

    @GetMapping("/getall")
    public ResponseEntity showAllMobile() throws Exception {
        try{
            return ResponseEntity.ok(mobileServiceImpl.showMobiles());
        }catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }


    @GetMapping("byName/{mobileName}")
    public ResponseEntity showAllMobileByName(@PathVariable String mobileName){
        try{
            return ResponseEntity.ok(mobileServiceImpl.showMobileByName(mobileName));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("byBrand/{mobileBrand}")
    public ResponseEntity showAllMobileByBrand(@PathVariable String mobileBrand){
        try{
            return ResponseEntity.ok(mobileServiceImpl.showMobileByBrand(mobileBrand));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addMobile(@RequestBody Mobile mobile){
        try{
            response = mobileServiceImpl.addMobile(mobile);
            LOG.log(Level.INFO, response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = e.getMessage();
            LOG.log(Level.INFO, response);
            return ResponseEntity.ok(response);
        }

    }

    @PutMapping("/update")
    public ResponseEntity updateMobile(@RequestBody Mobile mobile,@RequestParam Long lid){
        try{
            return ResponseEntity.ok(mobileServiceImpl.updateMobile(mobile,lid));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
