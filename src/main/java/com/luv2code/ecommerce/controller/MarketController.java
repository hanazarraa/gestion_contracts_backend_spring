package com.luv2code.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dao.ContractRepository;
import com.luv2code.ecommerce.dao.MarketRepository;
import com.luv2code.ecommerce.dao.OfferRepository;
import com.luv2code.ecommerce.entity.Market;
import com.luv2code.ecommerce.entity.Offer;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/markets")
public class MarketController {
	 @Autowired
	    private MarketRepository marketRepository;
	    //get all offers
	    @GetMapping("AllMarkets")
	    public List<Market> getAllMarkets(){
	        return this.marketRepository.findAll();
	    }
	    //get offer by id
	    @GetMapping("/{id}")
	    public Market getMarketById(@PathVariable(value = "id")long MarketId){

	        return this.marketRepository.findById(MarketId)
	                .orElseThrow(()->new ResourceNotFoundException("Market not found with id: "+MarketId));

	    }
	

}
