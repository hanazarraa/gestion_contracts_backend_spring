package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.ContractRepository;


import com.luv2code.ecommerce.dao.CustomerRepository;
import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.ContractPhoneNumber;
import com.luv2code.ecommerce.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;
import com.luv2code.*;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
  @RequestMapping(value = "/contracts")
public class ContractController {
    @Autowired
    private ContractRepository contractRepository;

    //get all contracts
    @GetMapping ("getContracts")
    public List<Contract> getAllContracts(){
        return this.contractRepository.findAll();
    }



    //get Contract by status
   /* @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Contract> getContractByStatus(@RequestParam(value = "status",required = false)String status){

        return  this.contractRepository.findByStatus(status);
        // return this.customerRepository.findByFname(fname);



        // .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+fname));

    }*/

    //get Contract by customer id
    @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Contract> getContractByCustomerAndStatus(@RequestParam(value = "customer_id",required = false)String customer_id,
                                                @RequestParam(value = "status",required = false)String status){

        if(customer_id==null ){
            if (status==null) {
                return this.contractRepository.findAll();
            }else {
                return this.contractRepository.findByStatus(status);
            }

       }else {
               Long customerId=Long.parseLong(customer_id);
                if (status!=null) {
                    return this.contractRepository.findByStatusAndCustomerid(customerId,status);
                }else {
                    return this.contractRepository.findByCustomerid(customerId);

                }




        }

        // return this.customerRepository.findByFname(fname);



        // .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+fname));

    }

    @GetMapping("/{id}")
    public Contract getContractById(@PathVariable(value = "id")long contractId){
        System.out.println(contractId);
        return this.contractRepository.findById(contractId)
               .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+contractId));

    }
    //create contract
    @PostMapping("/create")
   // @ResponseStatus(HttpStatus.CREATED)

    public Contract createContract( @RequestBody Contract contract){
    	contract.setStatus("A");
    	
    //	cph.setPhoneNmber(phoneNmber);
    	//contract.getContractsphonenumbers().add(e)
        return this.contractRepository.save(contract);
    }
    //update contract
    @PutMapping("/{id}")
    public Contract updateContract(@RequestBody Contract contract,@PathVariable("id") long contractId){
        Contract existig=this.contractRepository.findById(contractId).orElseThrow(()->new ResourceNotFoundException("contract not found with id: "+contractId));
        existig.setStatus(contract.getStatus());

        return this.contractRepository.save(existig);
        //return existig;
    }
    //delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id")long contractID){
        Contract existing=this.contractRepository.findById(contractID).orElseThrow(()->new ResourceNotFoundException("contract not found with id: "+contractID));
        this.contractRepository.delete(existing);
        return ResponseEntity.ok().build();
    }
}
