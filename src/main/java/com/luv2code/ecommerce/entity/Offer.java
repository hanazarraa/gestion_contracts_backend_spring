package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="offers")
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private long id;

    @Column(name = "offer_description")
    private String offerDescription;
    
    
    @ManyToOne
    @JoinColumn(name = "market_id",nullable = true)
    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference("market-offers")
    private Market market;
    
    public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "offer")

    @JsonManagedReference("offer-contracts")
    private List<Contract> contracts;
    
  

}
