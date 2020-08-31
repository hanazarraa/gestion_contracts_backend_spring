package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.CustomerRepository;
import com.luv2code.ecommerce.dao.InvoiceRepository;
import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Invoice;
//import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
//import net.kaczmarzyk.spring.data.jpa.domain.In;
//import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
//import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.hibernate.annotations.AnyMetaDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    //get all invoices
    @GetMapping("AllInvoices")
    public List<Invoice> getAllInvoices(){
        return this.invoiceRepository.findAll();
    }
    //get invoice by id
    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable(value = "id")long invoiceId){
        return this.invoiceRepository.findById(invoiceId)
                .orElseThrow(()->new ResourceNotFoundException("invoice not found with id: "+invoiceId));

    }


    //get Invoices by amount
   /* @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Invoice> getInvoiceByAmount(@RequestParam(value = "amount",required = false)float amount
                                                    ){

        return  this.invoiceRepository.findByAmount(amount);
        // return this.customerRepository.findByFname(fname);



        // .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+fname));

    }*/



    /*@GetMapping("all")
    public ResponseEntity<List<Invoice>> searchForInvoices(@SearchSpec Specification<Invoice> specs) {
        return new ResponseEntity<>(invoiceRepository.findAll(Specification.where(specs)), HttpStatus.OK);
    }*/


    //get Invoice by customer id
    @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Invoice> getInvoiceByCustomer(@RequestParam(value = "customer_id",required = false)long customer_id){
        System.out.println(customer_id);
        if (!isNumeric(customer_id)) {
            return this.invoiceRepository.findAll();
        }else{
        Optional<Customer> customer=customerRepository.findById(customer_id);
      
            return this.invoiceRepository.findByCustomerid(customer_id);
            // return this.customerRepository.findByFname(fname);
        }


        // .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+fname));

    }

    //create invoice
    @PostMapping("create")
    public Invoice createInvoice( @RequestBody Invoice invoice){
        return this.invoiceRepository.save(invoice);
    }
    //update invoice
    @PutMapping("/{id}")
    public Invoice updateInvoice(@RequestBody Invoice invoice,@PathVariable("id") long invoiceId){
        Invoice existig=this.invoiceRepository.findById(invoiceId).orElseThrow(()->new ResourceNotFoundException("Invoice not found with id: "+invoiceId));
        existig.setAmount(invoice.getAmount());

        return this.invoiceRepository.save(existig);
        //return existig;
    }
    //delete Invoice
    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id")long invoiceID){
        Invoice existing=this.invoiceRepository.findById(invoiceID).orElseThrow(()->new ResourceNotFoundException("Invoice not found with id: "+invoiceID));
        this.invoiceRepository.delete(existing);
        return ResponseEntity.ok().build();
    }


   /* @Transactional
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Invoice>> get(
            @And({
                    @Spec(path = "amount", params = "amount", spec = GreaterThan.class),
                   }) Specification<Invoice> spec,
            Sort sort,
            @RequestHeader HttpHeaders headers) {
        final PagingResponse response = carService.get(spec, headers, sort);
        return new ResponseEntity<Invoice>(response., returnHttpHeaders(response), HttpStatus.OK);
    }*/

}
