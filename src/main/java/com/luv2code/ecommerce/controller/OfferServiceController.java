package com.luv2code.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dao.OfferRepository;
import com.luv2code.ecommerce.dao.OfferServiceRepository;
import com.luv2code.ecommerce.entity.Offer;
import com.luv2code.ecommerce.entity.OfferService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/offer_services")
public class OfferServiceController {
	   @Autowired
	    private OfferServiceRepository offerserviceRepository;
	   //get all offers
	    @GetMapping("AllOffers_Services")
	    public List<OfferService> getAllOffers_Services(){
	        return this.offerserviceRepository.findAll();
	    }

}
