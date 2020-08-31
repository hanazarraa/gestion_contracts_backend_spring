package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query(value = "SELECT * FROM offers o WHERE o.offer_description like %?1% ",nativeQuery = true)
    List<Offer> findByOfferDescription(String offerDescription);
    @Query(value = "SELECT * FROM offers o WHERE o.market_id = ?1 ",nativeQuery = true)
    List<Offer> findByMarketid(Long market_id);

}
