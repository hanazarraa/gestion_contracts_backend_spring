package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="contracts_phone_numbers")
@Data
public class ContractPhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "phone_number_id",nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference("phone-numbers-contract")
    private PhoneNmber phoneNmber;
    @ManyToOne
    @JoinColumn(name = "contract_id",nullable = false,referencedColumnName = "contract_id")
    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference("contract-phone-numbers")
    private Contract contract;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PhoneNmber getPhoneNmber() {
        return phoneNmber;
    }

    public void setPhoneNmber(PhoneNmber phoneNmber) {
        this.phoneNmber = phoneNmber;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Date getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(Date dateActivation) {
        this.dateActivation = dateActivation;
    }

    @Column(name = "date_activation")
    private Date dateActivation;

}
