package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="contracts")
@Data
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
  private long contract_id;

    public long getContract_id() {
        return contract_id;
    }

    public void setContract_id(long contract_id) {
        this.contract_id = contract_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Offer getOffer() {
        return offer;
    }
    public Contract() {
    	
    }

    public Contract(long contract_id, String status, Customer customer, Offer offer,
			List<ContractPhoneNumber> contractsphonenumbers, List<ContractSIM> contracts_sims) {
		this.contract_id = contract_id;
		this.status = "A";
		this.customer = customer;
		this.offer = offer;
		this.contractsphonenumbers = contractsphonenumbers;
		this.contracts_sims = contracts_sims;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Column(name = "status")
  private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true,referencedColumnName = "customer_id")
    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference("customer-contracts")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="offer_id",nullable=true,referencedColumnName="offer_id")
    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference("offer-contracts")
    private Offer offer;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "contract")
    @JsonManagedReference("contract-phone-numbers")
    private List<ContractPhoneNumber> contractsphonenumbers;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "contract")
    @JsonManagedReference("contract-sims")
    private List<ContractSIM> contracts_sims;


    public List<ContractSIM> getContracts_sims() {
        return contracts_sims;
    }

    public void setContracts_sims(List<ContractSIM> contracts_sims) {
        this.contracts_sims = contracts_sims;
    }
    public List<ContractPhoneNumber> getContractsphonenumbers() {
        return contractsphonenumbers;
    }

    public void setContractsphonenumbers(List<ContractPhoneNumber> contractsphonenumbers) {
        this.contractsphonenumbers = contractsphonenumbers;
    }
}
