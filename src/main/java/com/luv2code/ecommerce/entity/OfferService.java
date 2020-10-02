package com.luv2code.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name="offer_services")
@Data
public class OfferService {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private  long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "offer_id",nullable = false,referencedColumnName = "offer_id")
	    @JsonIgnore
	    @ToString.Exclude
	    private Offer offer;
	    
	    @ManyToOne
	    @JoinColumn(name = "service_id",nullable = false,referencedColumnName = "service_id")
	    @JsonIgnore
	    @ToString.Exclude
	    private Service service;
	    
	    @Column(name = "price")
	    private Float price;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public Offer getOffer() {
			return offer;
		}

		public void setOffer(Offer offer) {
			this.offer = offer;
		}

		public Service getService() {
			return service;
		}

		public void setService(Service service) {
			this.service = service;
		}

		public Float getPrice() {
			return price;
		}

		public void setPrice(Float price) {
			this.price = price;
		}


}
