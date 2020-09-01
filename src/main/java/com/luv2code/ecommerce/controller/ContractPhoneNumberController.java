package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.ContractPhoneNumberRepository;
import com.luv2code.ecommerce.dao.ContractRepository;
import com.luv2code.ecommerce.dao.PhoneNumberRepository;
import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.ContractPhoneNumber;
import com.luv2code.ecommerce.entity.PhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/contracts_phone_numbers")
public class ContractPhoneNumberController {

    @Autowired
    ContractPhoneNumberRepository contractPhoneNumberRepository;
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    PhoneNumberRepository phoneNumberRepository;

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
    @PostMapping("/create")
    public ContractPhoneNumber createContractPhoneNumber( @RequestBody ContractPhoneNumber contractPhoneNumber){
        System.out.println(contractPhoneNumber.getPhoneNumber().getStatus());
        Contract contract =contractPhoneNumber.getContract();
       
          contract.getContractsphonenumbers().add(contractPhoneNumber);
         //     this.contractRepository.save(contract);
              
              //System.out.println(contractPhoneNumber.getPhoneNumber());

      
        PhoneNumber phone_number=contractPhoneNumber.getPhoneNumber();
       phone_number.getContractsphonenumbers().add(contractPhoneNumber);
       // this.phoneNumberRepository.save(phone_number);
        contractPhoneNumber.setDateActivation(new Date());
      //  System.out.println("date activation "+contractPhoneNumber.getDateActivation());
        //System.out.println("contract id "+contractPhoneNumber.getContract().getContract_id());
       // System.out.println("phone number id "+contractPhoneNumber.getPhoneNumber().getId());
        return this.contractPhoneNumberRepository.save(contractPhoneNumber);
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
