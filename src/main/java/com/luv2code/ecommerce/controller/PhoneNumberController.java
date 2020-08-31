package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.OfferRepository;

import com.luv2code.ecommerce.dao.PhoneNumberRepository;
import com.luv2code.ecommerce.entity.Offer;
import com.luv2code.ecommerce.entity.PhoneNmber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/phone_numbers")
public class PhoneNumberController {
    @Autowired
    private PhoneNumberRepository phoneRepository;


    //get all phone numbers
    @GetMapping("AllphoneNumbers")
    public List<PhoneNmber> getAllphoneNumbers(){
        return this.phoneRepository.findAll();
    }
    //find free and d assigned phone numbers
    @GetMapping("free")
    public List<PhoneNmber> getFreePhoneNumbers(){
    	return this.phoneRepository.findByStatus("F");
    }

    //get phoneNumber
    @RequestMapping(value="/search",method = RequestMethod.GET)
      @ResponseBody
    public List<PhoneNmber> getPhoneNumberAndStatus(@RequestParam(value = "phone",required = false)String phone,
                                                    @RequestParam(value = "status",required = false)String status){
        if(phone!=null){
            if(status!=null){
                return this.phoneRepository.findByPhonenumberOrStatus(phone,status);

            }else {
                return this.phoneRepository.findByPhonenumber(phone);
            }

        }else {
            if(status!=null){
                return this.phoneRepository.findByStatus(status);

            }else {
                return this.phoneRepository.findAll();
            }

        }
    }

    //get phone number by id
    @GetMapping("/{id}")
    public PhoneNmber getPhoneNumberById(@PathVariable(value = "id")long PhoneId){
        return this.phoneRepository.findById(PhoneId)
                .orElseThrow(()->new ResourceNotFoundException("phone number not found with id: "+PhoneId));

    }





    //create phone number
    @PostMapping("create")
    public PhoneNmber createPhoneNumber( @RequestBody PhoneNmber phoneNmber){
        return this.phoneRepository.save(phoneNmber);
    }
    //update Phone number
    @PutMapping("/{id}")
    public PhoneNmber updatePhoneNumber(@RequestBody PhoneNmber phoneNmber,@PathVariable("id") long phoneId){
        PhoneNmber existig=this.phoneRepository.findById(phoneId).orElseThrow(()->new ResourceNotFoundException("phone number not found with id: "+phoneId));
        existig.setPhonenumber(phoneNmber.getPhonenumber());

        return this.phoneRepository.save(existig);
        //return existig;
    }
    //delete phone number
    @DeleteMapping("/{id}")
    public ResponseEntity<PhoneNmber> deletephonenumber(@PathVariable("id")long phoneId){
        PhoneNmber existing=this.phoneRepository.findById(phoneId).orElseThrow(()->new ResourceNotFoundException("Phone number not found with id: "+phoneId));
        this.phoneRepository.delete(existing);
        return ResponseEntity.ok().build();
    }
}
