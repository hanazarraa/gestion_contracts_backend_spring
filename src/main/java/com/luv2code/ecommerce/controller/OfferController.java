package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.ContractRepository;
import com.luv2code.ecommerce.dao.InvoiceRepository;
import com.luv2code.ecommerce.dao.MarketRepository;
import com.luv2code.ecommerce.dao.OfferRepository;
import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.Invoice;
import com.luv2code.ecommerce.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    MarketRepository marketRepository;
    //get all offers
    @GetMapping("AllOffers")
    public List<Offer> getAllOffers(){
        return this.offerRepository.findAll();
    }
    //get offer by id
    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable(value = "id")long OfferId){

        return this.offerRepository.findById(OfferId)
                .orElseThrow(()->new ResourceNotFoundException("Offer not found with id: "+OfferId));

    }


    //get offer by description
    /*@RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Offer> getOffersByDescription(@RequestParam(value = "description",required = false)String description){
           if(description!=null) {
               return this.offerRepository.findByOfferDescription(description);
           }else {
               return this.offerRepository.findAll();
           }
        // return this.customerRepository.findByFname(fname);



        // .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+fname));

    }
*/
    @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Offer> getOffersByMarket(@RequestParam(value = "market_id",required = false)String market_id){
        System.out.println(market_id);
        if(market_id!=null) {
            Long marketId = Long.parseLong(market_id);
            Optional market=this.marketRepository.findById(marketId);
            return this.offerRepository.findByMarketid(marketId);

        }else{
            throw new ResourceNotFoundException("offer not found with market_id" +market_id);

            }
        }


        // return this.customerRepository.findByFname(fname);



        // .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+fname));






    //create offer
    @PostMapping("create")
    public Offer createOffer( @RequestBody Offer Offer){
        return this.offerRepository.save(Offer);
    }
    //update Offer
    @PutMapping("/{id}")
    public Offer updateOffer(@RequestBody Offer offer,@PathVariable("id") long OfferId){
        Offer existig=this.offerRepository.findById(OfferId).orElseThrow(()->new ResourceNotFoundException("Offer not found with id: "+OfferId));
        existig.setOfferDescription(offer.getOfferDescription());

        return this.offerRepository.save(existig);
        //return existig;
    }
    //delete Offer
    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable("id")long OfferID){
        Offer existing=this.offerRepository.findById(OfferID).orElseThrow(()->new ResourceNotFoundException("Offer not found with id: "+OfferID));
        this.offerRepository.delete(existing);
        return ResponseEntity.ok().build();
    }
}
