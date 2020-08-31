package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="SIM")
@Data
public class Sim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sim_id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    @Column(name = "serial_number")
    private String serial_number;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sim")
    @JsonIgnore
    @JsonManagedReference("sim-contracts")
    private List<ContractSIM> contractssims;

    public List<ContractSIM> getContractssims() {
        return contractssims;
    }

    public void setContractssims(List<ContractSIM> contractssims) {
        this.contractssims = contractssims;
    }
}
