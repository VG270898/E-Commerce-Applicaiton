package com.mobile.service;

import com.mobile.model.Mobile;

import java.util.List;

public interface IMobileService {
    public String addMobile(Mobile Mobile) throws Exception;
    public List<Mobile> showMobiles() throws Exception;
    public List<Mobile> showMobileByName(String Name) throws Exception;
    public List<Mobile> showMobileByBrand(String Brand) throws Exception;
    public String updateMobile(Mobile mobile,Long mid) throws Exception;
}
