package com.mobile.repository;

import com.mobile.model.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MobileRepo extends JpaRepository<Mobile,Long> {

    List<Mobile>findByMobileName(String mobileName);

    List<Mobile>findByMobileBrand(String mobileBrand);

}
