package com.laptop.service;

import com.laptop.model.Laptop;

import java.util.List;

public interface ILaptopService {
    public String addLaptop(Laptop laptop) throws Exception;
    public List<Laptop> showLaptops() throws Exception;
    public List<Laptop> showLaptopByName(String Name) throws Exception;
    public List<Laptop> showLaptopByBrand(String Brand) throws Exception;
    public String updateLaptop(Laptop laptop,Long lid) throws Exception;
}
