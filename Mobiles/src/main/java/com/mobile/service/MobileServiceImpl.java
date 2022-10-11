package com.mobile.service;

import com.mobile.model.Mobile;
import com.mobile.repository.MobileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileServiceImpl implements IMobileService{
    @Autowired
    private MobileRepo mobileRepo;

    @Override
    public String addMobile(Mobile mobile) throws Exception {
        if (mobile.getMobileBrand() == null) {
            throw new Exception("Fill Brand Value");
        } else if (mobile.getMobileName() == null) {
            throw new Exception("Fill Mobile Name");
        } else if (mobile.getMobilePrice() == null) {
            throw new Exception("Fill Mobile Price");
        } else {
            Long mobileId = mobileRepo.save(mobile).getMid();
            return "Mobile successfully Added with Mobile Id " + mobileId;
        }
    }

    @Override
    public List<Mobile> showMobiles() throws Exception {
        if(mobileRepo.findAll().isEmpty()){
            throw new Exception("No Mobile Found");
        }else {
            return mobileRepo.findAll();
        }
    }

    @Override
    public List<Mobile> showMobileByName(String name) throws Exception {
        if(mobileRepo.findByMobileName(name).isEmpty()){
            throw new Exception("Mobile with Name "+ name+" not Found");
        }else {
            return mobileRepo.findByMobileName(name);
        }
    }

    @Override
    public List<Mobile> showMobileByBrand(String brand) throws Exception {
        if(mobileRepo.findByMobileBrand(brand).isEmpty()){
            throw new Exception("Mobile with Brand "+ brand+" not Found");
        }else {
            return mobileRepo.findByMobileBrand(brand);
        }
    }

    @Override
    public String updateMobile(Mobile mobile, Long lid) throws Exception {
        if(lid==null){
            throw new Exception("Fill Mobile Id");
        }else if (mobile.getMobileBrand() == null) {
            throw new Exception("Fill Brand Value");
        } else if (mobile.getMobileName() == null) {
            throw new Exception("Fill Mobile Name");
        } else if (mobile.getMobilePrice() == null) {
            throw new Exception("Fill Mobile Price");
        } else {
            Long mobileId = mobileRepo.save(mobile).getMid();
            return "Mobile successfully Updated with Mobile Id " + mobileId;
        }
    }
}
