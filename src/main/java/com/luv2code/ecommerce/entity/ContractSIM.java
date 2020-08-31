package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="contracts_SIMS")
@Data
public class ContractSIM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  long id;
    @ManyToOne
    @JoinColumn(name = "contract_id",nullable = false,referencedColumnName = "contract_id")
    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference("contract-sims")
    private Contract contract;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }


    @ManyToOne
    @JoinColumn(name = "SIM_id",nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference("sim-contracts")
    private Sim sim;

    public Sim getSim() {
        return sim;
    }

    public void setSim(Sim sim) {
        this.sim = sim;
    }
}
