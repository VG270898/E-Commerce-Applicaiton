package com.laptop.controller;

import com.laptop.model.Laptop;
import com.laptop.service.LaptopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/laptop")
public class LaptopController {

    @Autowired
    private LaptopServiceImpl laptopServiceImpl;

    @GetMapping("/getall")
    public ResponseEntity showAllLaptop() throws Exception {
        try{
            return ResponseEntity.ok(laptopServiceImpl.showLaptops());
        }catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }


    @GetMapping("byName/{laptopName}")
    public ResponseEntity showAllLaptopByName(@PathVariable String laptopName){
        try{
            return ResponseEntity.ok(laptopServiceImpl.showLaptopByName(laptopName));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("byBrand/{laptopBrand}")
    public ResponseEntity showAllLaptopByBrand(@PathVariable String laptopBrand){
        try{
            return ResponseEntity.ok(laptopServiceImpl.showLaptopByBrand(laptopBrand));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addLaptop(@RequestBody Laptop laptop){
        try{
            return ResponseEntity.ok(laptopServiceImpl.addLaptop(laptop));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateLaptop(@RequestBody Laptop laptop,@RequestParam Long lid){
        try{
            return ResponseEntity.ok(laptopServiceImpl.updateLaptop(laptop,lid));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }


}
