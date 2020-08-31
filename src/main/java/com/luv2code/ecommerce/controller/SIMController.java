package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.PhoneNumberRepository;
import com.luv2code.ecommerce.dao.SIMRepository;
import com.luv2code.ecommerce.entity.Offer;
import com.luv2code.ecommerce.entity.PhoneNmber;
import com.luv2code.ecommerce.entity.Sim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

//import com.luv2code.ecommerce.exception.ResourceNotFoundException;

import java.util.List;
@RestController
@RequestMapping("/SIMS")
public class SIMController {
    @Autowired
    private SIMRepository simRepository;
    //get all sims cards
    @GetMapping("AllSIMS_cards")
    public List<Sim> getAllSIMS(){
        return this.simRepository.findAll();
    }
    //get sim card by id
    @GetMapping("/{id}")
    public Sim getSIMById(@PathVariable(value = "id")long SIMId){
        return this.simRepository.findById(SIMId)
                .orElseThrow(()->new ResourceNotFoundException("SIM Card not found with id: "+SIMId));

    }


    //get Sim by serial number
    @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Sim> getSIMSBySerialNumber(@RequestParam(value = "serial_number",required = false)String serialNumber){
          if(serialNumber!=null){
              return  this.simRepository.findBySerialNumber(serialNumber);

          }else {
              return this.simRepository.findAll();
          }
        // return this.customerRepository.findByFname(fname);



        // .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+fname));

  }



    //create sim card
    @PostMapping("create")
    public Sim createSIM( @RequestBody Sim sim){
        return this.simRepository.save(sim);
    }
    //update sim card
    @PutMapping("/{id}")
    public Sim updateSIM(@RequestBody Sim sim,@PathVariable("id") long SIMId){
        Sim existig=this.simRepository.findById(SIMId).orElseThrow(()->new ResourceNotFoundException("SIM  not found with id: "+SIMId));
        existig.setSerial_number(sim.getSerial_number());

        return this.simRepository.save(existig);
        //return existig;
    }
    //delete sil
    @DeleteMapping("/{id}")
    public ResponseEntity<PhoneNmber> deletesim(@PathVariable("id")long SIMId){
        Sim existing=this.simRepository.findById(SIMId).orElseThrow(()->new ResourceNotFoundException("SIM not found with id: "+SIMId));
        this.simRepository.delete(existing);
        return ResponseEntity.ok().build();
    }
}
