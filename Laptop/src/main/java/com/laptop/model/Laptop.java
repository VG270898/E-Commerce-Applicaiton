package com.laptop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lid;
    private String laptopName;
    private String laptopPrice;
    private String laptopBrand;
    private String laptopDescription;

    public Laptop(String laptopName, String laptopPrice, String laptopBrand, String laptopDescription) {
        this.laptopName = laptopName;
        this.laptopPrice = laptopPrice;
        this.laptopBrand = laptopBrand;
        this.laptopDescription = laptopDescription;
    }

    public Laptop() {
        super();
    }



    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public String getLaptopPrice() {
        return laptopPrice;
    }

    public void setLaptopPrice(String laptopPrice) {
        this.laptopPrice = laptopPrice;
    }

    public String getLaptopBrand() {
        return laptopBrand;
    }

    public void setLaptopBrand(String laptopBrand) {
        this.laptopBrand = laptopBrand;
    }

    public String getLaptopDescription() {
        return laptopDescription;
    }

    public void setLaptopDescription(String laptopDescription) {
        this.laptopDescription = laptopDescription;
    }
}
