package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.ecommerce.entity.OfferService;
import com.luv2code.ecommerce.entity.PhoneNumber;

public interface OfferServiceRepository extends JpaRepository<OfferService, Long> {

}
