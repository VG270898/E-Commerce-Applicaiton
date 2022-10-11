package com.laptop.service;

import com.laptop.model.Laptop;
import com.laptop.repository.LaptopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopServiceImpl implements ILaptopService{

    @Autowired
    private LaptopRepo laptopRepo;

    @Override
    public String addLaptop(Laptop laptop) throws Exception {
        if (laptop.getLaptopBrand() == null) {
            throw new Exception("Fill Brand Value");
        } else if (laptop.getLaptopName() == null) {
            throw new Exception("Fill Laptop Name");
        } else if (laptop.getLaptopPrice() == null) {
            throw new Exception("Fill Laptop Price");
        } else {
            Long laptopId = laptopRepo.save(laptop).getLid();
            return "Laptop successfully Added with Laptop Id " + laptopId;
        }
    }

    @Override
    public List<Laptop> showLaptops() throws Exception {
        if(laptopRepo.findAll().isEmpty()){
            throw new Exception("No Laptop Found");
        }else {
            return laptopRepo.findAll();
        }
    }

    @Override
    public List<Laptop> showLaptopByName(String name) throws Exception {
        if(laptopRepo.findByLaptopName(name).isEmpty()){
            throw new Exception("Laptop with Name "+ name+" not Found");
        }else {
            return laptopRepo.findByLaptopName(name);
        }
    }

    @Override
    public List<Laptop> showLaptopByBrand(String brand) throws Exception {
        if(laptopRepo.findByLaptopBrand(brand).isEmpty()){
            throw new Exception("Laptop with Brand "+ brand+" not Found");
        }else {
            return laptopRepo.findByLaptopBrand(brand);
        }
    }

    @Override
    public String updateLaptop(Laptop laptop, Long lid) throws Exception {
        if(lid==null){
            throw new Exception("Fill Laptop Id");
        }else if (laptop.getLaptopBrand() == null) {
            throw new Exception("Fill Brand Value");
        } else if (laptop.getLaptopName() == null) {
            throw new Exception("Fill Laptop Name");
        } else if (laptop.getLaptopPrice() == null) {
            throw new Exception("Fill Laptop Price");
        } else {
            Long laptopId = laptopRepo.save(laptop).getLid();
            return "Laptop successfully Updated with Laptop Id " + laptopId;
        }
    }
}
