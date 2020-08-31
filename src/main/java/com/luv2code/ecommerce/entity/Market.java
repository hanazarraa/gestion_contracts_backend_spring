package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="markets")
@Data
public class Market {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "market_id")
	    private long id;
	    @Column(name = "market_description")
	    private String marketDescription;
	    
	    @OneToMany(cascade = CascadeType.ALL,mappedBy = "market")
	    @JsonManagedReference("market-offers")
	    private List<Offer> offers;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getMarketDescription() {
			return marketDescription;
		}

		public void setMarketDescription(String marketDescription) {
			this.marketDescription = marketDescription;
		}

		public List<Offer> getOffers() {
			return offers;
		}

		public void setOffers(List<Offer> offers) {
			this.offers = offers;
		}

	    

}
