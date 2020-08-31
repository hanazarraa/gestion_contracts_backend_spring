package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Offer;
import com.luv2code.ecommerce.entity.Sim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SIMRepository  extends JpaRepository<Sim, Long> {
    @Query(value = "SELECT * FROM SIM s WHERE s.serial_number like %?1% ",nativeQuery = true)
    List<Sim> findBySerialNumber(String serialNumber);

}
