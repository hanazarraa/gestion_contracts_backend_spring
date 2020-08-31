package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity

@Table(name="customer", uniqueConstraints = {@UniqueConstraint(columnNames = {"cin"})})
@Data
@JsonIgnoreProperties("contracts")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private  long customer_id;
    @Column(name = "fname")

    private  String fname;
    @Column(name = "lastname")

    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "cin",unique=true)
    private String cin;

    @Column(name = "birth_date")
    private Date birthdate;

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public long getCustomer_id() {
        return customer_id;
    }
    public Customer(){}
    public Customer(long customer_id, String fname, Date birthdate,String lastname, String email, String address, List<Invoice> invoices,String cin) {
        this.customer_id = customer_id;
        this.fname = fname;
        this.lastname = lastname;
        this.birthdate=birthdate;
        this.email = email;
        this.address = address;
        this.cin=cin;
        this.invoices = invoices;
    }

    public Customer(String fname, String lastname, Date birthdate,String email, String address, List<Invoice> invoices) {
        this.fname = fname;
        this.birthdate=birthdate;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.invoices = invoices;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Column(name = "address")

    private String address;
   @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
   @JsonManagedReference("customer-invoices")
   private List<Invoice> invoices;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    @JsonManagedReference("customer-contracts")
    private List<Contract> contracts;

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
