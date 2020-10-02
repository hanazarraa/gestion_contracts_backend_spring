package com.luv2code.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luv2code.ecommerce.entity.Service;
import com.luv2code.ecommerce.entity.User;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query(value = "SELECT * FROM services s WHERE s.name = ?1",nativeQuery = true)
    List<Service> findByName(String name);
    
    @Query(value="select s.* from services s,offer_services os where os.offer_id=?1 and s.service_id=os.service_id",nativeQuery=true)
    List<Service> findServices(String offer);
  
}
