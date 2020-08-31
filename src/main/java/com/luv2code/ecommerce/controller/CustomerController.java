package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.CustomerRepository;
import com.luv2code.ecommerce.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    //get all customers
    @GetMapping("AllCustomers")
    public List<Customer> getAllCustomers(){
    	System.out.println(this.customerRepository.findAll());
        return this.customerRepository.findAll();
    }
    //get Customer by id
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable (value = "id")long customerId){
        return this.customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+customerId));

    }

    //get Customer by firstname and lastname
    @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomerByFname_lastname(@RequestParam(value = "fname",required = false)String fname,
                                                      @RequestParam(value = "lastname",required = false)String lastname,
                                                      @RequestParam(value = "cin",required = false)String cin) {

        if (fname != null) {
            if (lastname != null) {
                if (cin != null) {
                    return this.customerRepository.findByLastnameOrFnameOrCin(fname, lastname, cin);

                } else {
                    return this.customerRepository.findByLastnameOrFname(fname, lastname);
                }

            }else{
                if(cin !=null){
                    return this.customerRepository.findByLastnameAndCin(lastname, cin);

                }else {
                    return this.customerRepository.findByFname(fname);
                }
            }

        }else{
            if(lastname!=null){
                if(cin!=null){
                    return this.customerRepository.findByLastnameAndCin( lastname, cin);

                }else {
                    return this.customerRepository.findByLastname(lastname);

                }


            }else {
                if(cin!=null){
                    return this.customerRepository.findByCin( cin);

                }else {
                    return this.customerRepository.findAll();

                }

            }
        }

        // return this.customerRepository.findByFname(fname);


        // .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+fname));

    }


    //create customer
    @PostMapping("/create")
    public Customer createCustomer( @RequestBody Customer customer){
        return this.customerRepository.save(customer);
    }
    //update customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@RequestBody Customer customer,@PathVariable("id") long customerId){
        Customer existig=this.customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+customerId));
        existig.setFname(customer.getFname());
        existig.setLastname(customer.getLastname());
        existig.setEmail(customer.getEmail());
        existig.setAddress(customer.getAddress());
        existig.setBirthdate(customer.getBirthdate());
        existig.setCin(customer.getCin());
        return this.customerRepository.save(existig);
        //return existig;
    }
    //delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id")long customerID){
        Customer existing=this.customerRepository.findById(customerID).orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+customerID));
        this.customerRepository.delete(existing);
        return ResponseEntity.ok().build();
    }

}
