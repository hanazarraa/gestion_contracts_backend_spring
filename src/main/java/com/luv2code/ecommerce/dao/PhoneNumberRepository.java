package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Offer;
import com.luv2code.ecommerce.entity.PhoneNmber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneNumberRepository  extends JpaRepository<PhoneNmber, Long> {
    @Query(value = "SELECT * FROM phone_numbers ph WHERE ph.phonenumber like %?1% and ph.status = ?2 ",nativeQuery = true)
    List<PhoneNmber> findByPhonenumberOrStatus(@Param("phone_number") String phone_number,
                                               @Param("status") String status);
    @Query(value = "SELECT * FROM phone_numbers ph WHERE ph.phonenumber like %?1% ",nativeQuery = true)
    List<PhoneNmber> findByPhonenumber(@Param("phone_number") String phone_number);
    @Query(value = "SELECT * FROM phone_numbers ph WHERE ph.status = ?1 ",nativeQuery = true)
    List<PhoneNmber> findByStatus(@Param("status") String status);

}