package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "invoices")
//known bug
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Invoice_id")
    private  long invoice_id;

    public long getInvoice_id() {
        return invoice_id;
    }
   public Invoice(){}
    public Invoice(long invoice_id, Customer customer, float amount) {
        this.invoice_id = invoice_id;
        this.customer = customer;
        this.amount = amount;
    }

    public Invoice(Customer customer, float amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public void setInvoice_id(long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = true)
    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference("customer-invoices")
    private Customer customer;
    @Column(name = "amount")
    private float amount;
}
