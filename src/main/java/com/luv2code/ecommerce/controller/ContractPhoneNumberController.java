package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.ContractPhoneNumberRepository;
import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.ContractPhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping(value = "/contracts_phone_numbers")
public class ContractPhoneNumberController {

    @Autowired
    ContractPhoneNumberRepository contractPhoneNumberRepository;

    //get all contracts
    @GetMapping ("getAll")
    public List<ContractPhoneNumber> getAll(){
        return this.contractPhoneNumberRepository.findAll();
    }
    @GetMapping("/{id}")
    public ContractPhoneNumber getContractPhoneNumberById(@PathVariable(value = "id")long Id){
        return this.contractPhoneNumberRepository.findById(Id)
                .orElseThrow(()->new ResourceNotFoundException("contract phone number not found with id: "+Id));

    }

    @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<ContractPhoneNumber> getContractPhoneNumberByContractAndPhoneId(@RequestParam(value = "contract_id",required = false) String contract_id,
                                                                                @RequestParam(value = "phone_id",required = false) String phone_id
                                                                            ) {

         if(contract_id==null ){
             if(phone_id==null) {
                 return this.contractPhoneNumberRepository.findAll();
             }else {
                 return this.contractPhoneNumberRepository.findByPhoneNumberid(Long.parseLong(phone_id));
             }
         }else {
              if (phone_id==null) {
             return this.contractPhoneNumberRepository.findByContractid(Long.parseLong(contract_id));
         }else {
              return this.contractPhoneNumberRepository.findByContractidAndPhoneNumberid(Long.parseLong(contract_id),Long.parseLong(phone_id));
              }
         }

    }
        }
