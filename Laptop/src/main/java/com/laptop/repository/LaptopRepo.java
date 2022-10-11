package com.laptop.repository;

import com.laptop.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepo extends JpaRepository<Laptop,Long> {
    List<Laptop>findByLaptopName(String laptopName);
    List<Laptop>findByLaptopBrand(String laptopBrand);
}
