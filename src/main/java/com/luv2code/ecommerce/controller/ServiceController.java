package com.luv2code.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dao.ServiceRepository;
import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Service;
import com.luv2code.ecommerce.entity.User;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/services")
public class ServiceController {
	@Autowired
    ServiceRepository serviceRepository;
    //get all users
    @GetMapping("getAll")
    public List<Service> getAllServices(){
        return this.serviceRepository.findAll();
    }
    @GetMapping("getServices") 
    public List<Service> getServicesByOffer(@RequestParam(value = "offer_id",required = false)String offer){
    	System.out.println(offer);
    	return this.serviceRepository.findServices(offer);
    }
  //get Service by id
    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable (value = "id")long serviceId){
        return this.serviceRepository.findById(serviceId)
                .orElseThrow(()->new ResourceNotFoundException("service not found with id: "+serviceId));

    }

}
