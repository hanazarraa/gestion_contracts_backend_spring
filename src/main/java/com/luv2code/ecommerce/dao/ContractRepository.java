package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByStatus(String status);
    @Query(value = "SELECT * FROM contracts c WHERE c.customer_id = ?1 ",nativeQuery = true)
    List<Contract> findByCustomerid(long customer_id);
    @Query(value = "SELECT * FROM contracts c WHERE c.customer_id = ?1 and c.status= ?2",nativeQuery = true)
    List<Contract> findByStatusAndCustomerid(long customer_id,String status);

}
